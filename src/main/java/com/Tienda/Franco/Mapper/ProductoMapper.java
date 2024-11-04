package com.Tienda.Franco.Mapper;

import org.springframework.stereotype.Component;
import com.Tienda.Franco.DTO.ProductoDTO;
import com.Tienda.Franco.Model.Producto;

@Component
public class ProductoMapper {

    // Convertir de Producto a ProductoDTO
    public ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        return ProductoDTO.builder() 
                .id(producto.getId())    
                .name(producto.getNombre())
                .color(producto.getColor())
                .precio(producto.getPrecio())
                .build();
    }

    // Convertir de ProductoDTO a Producto
    public Producto toEntity(ProductoDTO productoDTO) {  
        if (productoDTO == null) {
            throw new IllegalArgumentException("El productoDTO no puede ser nulo");
        }

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getName());
        producto.setColor(productoDTO.getColor());
        producto.setPrecio(productoDTO.getPrecio());
        return producto;   
    }
}
// listo
