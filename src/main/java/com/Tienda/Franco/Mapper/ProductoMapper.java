package com.Tienda.Franco.Mapper;

import org.springframework.stereotype.Component;

import com.Tienda.Franco.DTO.ProductoDTO;
import com.Tienda.Franco.Model.Producto;

@Component
public class ProductoMapper {



    public static ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        return ProductoDTO.builder() 
                .id(producto.getId())    
                .name(producto.getNombre())
                .color(producto.getColor())
                .precio(producto.getPrecio())
                .build();
    }

    public static Producto toEntity(ProductoDTO productoDTO) {  
        if (productoDTO == null) {
            throw new IllegalArgumentException("La personaDTO no puede ser nula");
        }

        Producto producto = new Producto();
        producto.setNombre(productoDTO.getName());
        producto.setColor(productoDTO.getColor());
        producto.setPrecio(productoDTO.getPrecio());
        return producto;   
    }
}
// listo
