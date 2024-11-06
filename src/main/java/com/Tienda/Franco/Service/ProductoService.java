package com.Tienda.Franco.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tienda.Franco.DTO.ProductoDTO;
import com.Tienda.Franco.Model.Producto;
import com.Tienda.Franco.Repository.ProductoRepository;
import com.Tienda.Franco.Mapper.ProductoMapper;



@Service
public class ProductoService {
    @Autowired
    private final ProductoRepository productoRepository;
    @Autowired
    private final ProductoMapper productoMapper;


    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    public List<ProductoDTO> getAllProductos() {
        if (productoRepository.findAll().isEmpty()) {
            throw new RuntimeException("No se encontraron productos");
        }

        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDTOProducto)
                .collect(Collectors.toList());
    }

    public Optional<ProductoDTO> getProductoById(Long id) {
        if (productoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("No se encontraron productos");
        }
        return productoRepository.findById(id).map(productoMapper::toDTOProducto);
    }

    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);


        Producto savedProducto = productoRepository.save(producto);
        return productoMapper.toDTOProducto(savedProducto);
    }

    public void deleteProducto(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("El producto no existe");
        }
    }

    public ProductoDTO updateStockProducto(Long productoId, int nuevoStock) {
        return productoRepository.findById(productoId)
            .map(producto -> {
                int stockActual = producto.getStock();
                int stockFinal = stockActual + nuevoStock;
                if (stockFinal < 0) {
                    throw new IllegalArgumentException("El stock no puede ser negativo.");
                }

                producto.setStock(stockFinal);
                return productoMapper.toDTOProducto(productoRepository.save(producto));
            })
            .orElseThrow(() -> new RuntimeException("Producto no encontrada con id: " + productoId));
    }
}