package com.Tienda.Franco.Mapper;

import org.springframework.stereotype.Component;

import com.Tienda.Franco.DTO.ProductoDTO;
import com.Tienda.Franco.Model.Producto;

@Component
public class ProductoMapper {

    // Mapea de la entidad Producto a ProductoDTO
    public ProductoDTO toDTOProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }

        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre()) 
                .precio(producto.getPrecio())  
                .stock(producto.getStock())  
                .categoria(producto.getTipo())  
                .build();
    }

    // Mapea de ProductoDTO a la entidad Producto
    public Producto toEntity(ProductoDTO productoDTO) {
        if (productoDTO == null) {
            throw new IllegalArgumentException("El ProductoDTO no puede ser nulo");
        }

        Producto producto = new Producto();
        producto.setId(productoDTO.getId()); 
        producto.setNombre(productoDTO.getNombre()); 
        producto.setPrecio(productoDTO.getPrecio()); 
        producto.setStock(productoDTO.getStock()); 
        producto.setTipo(productoDTO.getCategoria()); 
        return producto;
    }
}
