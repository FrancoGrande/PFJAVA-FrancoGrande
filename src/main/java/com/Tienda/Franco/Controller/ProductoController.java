package com.Tienda.Franco.Controller;

import com.Tienda.Franco.DTO.ApiResponseMsg;
import com.Tienda.Franco.DTO.ProductoDTO;
import com.Tienda.Franco.Model.Producto;
import com.Tienda.Franco.Service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // Importación correcta

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponseMsg> getAllProductos() {
        try {
            List<ProductoDTO> productos = productoService.getAllProductos();
            return ResponseEntity.ok(new ApiResponseMsg("Lista de productos", productos));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("No hay productos", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseMsg> getProductoById(@PathVariable Long id) {
        try {
            Optional<ProductoDTO> productoOpt = productoService.getProductoById(id);
            return productoOpt.map(producto -> ResponseEntity.ok(new ApiResponseMsg("Producto encontrada", producto)))
                        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                        .body(new ApiResponseMsg("Error: Producto no encontrada", null)));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                .body(new ApiResponseMsg("Error: " + e.getMessage(), null));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseMsg> createProducto(@RequestBody ProductoDTO productoDTO) {
        try {
            ProductoDTO createdProducto = productoService.saveProducto(productoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseMsg("Producto creada", createdProducto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al crear producto", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMsg> deleteProducto(@PathVariable Long id) {
        try {
            productoService.deleteProducto(id);
            return ResponseEntity.ok(new ApiResponseMsg("Producto eliminada", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: No se pudo eliminar el producto", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ApiResponseMsg> updateStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        try {
            ProductoDTO updatedProducto = productoService.updateStockProducto(id, nuevoStock);
            return ResponseEntity.ok(new ApiResponseMsg("Stock actualizado", updatedProducto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: " + e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseMsg("Producto no encontrada con id: " + id, null));
        }
    }
}