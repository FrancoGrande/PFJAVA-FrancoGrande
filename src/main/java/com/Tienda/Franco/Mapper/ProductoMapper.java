package com.Tienda.Franco.Mapper;

import org.springframework.stereotype.Component;

import com.Tienda.Franco.DTO.ProductoDTO;
import com.Tienda.Franco.Model.Producto;

@Component
public class ProductoMapper {

    public ProductoDTO toDTOProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }



        return ProductoDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .stock(producto.getStock())
                .build();
    }

    public Producto toEntity(ProductoDTO productoDTO) {
        if (productoDTO == null) {
            throw new IllegalArgumentException("El productoDTO no puede ser nulo");
        }

        Producto producto = new Producto();
        producto.setId(productoDTO.getId());
        producto.setNombre(productoDTO.getNombre());
        producto.setStock(productoDTO.getStock());
        return producto;
    }
}
