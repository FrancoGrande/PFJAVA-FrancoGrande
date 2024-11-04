package com.Tienda.Franco.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tienda.Franco.Mapper.ProductoMapper; 
import com.Tienda.Franco.DTO.ProductoDTO;
import com.Tienda.Franco.Model.Producto;
import com.Tienda.Franco.Repository.ProductoRepository;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoMapper productoMapper;

    public ProductoService(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    public List<ProductoDTO> obtenerTodosLosProducto() {
        if (productoRepository.findAll().isEmpty()) {
            throw new RuntimeException("No hay productos");
        }
        return productoRepository.findAll().stream()
                .map(productoMapper::toDTOProducto)
                .collect(Collectors.toList());
    }

    public List<Producto> obtenerProductoPorId(Class<Integer> id) {
        if (productoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("No hay productos");
        }
        return productoMapper.findById(class1).map(productoMapper::toDTOProducto);
    }

    public ProductoDTO agregarUnProducto(Producto producto2) {
        Producto producto = productoMapper.toEntity(producto2);
        
        if(producto2.getProductoIds() != null && !producto2.getProductoIds().isEmpty()) {
            List<Producto> productos = new HashSet<>();

            for (long productoId : producto2.getProductoIds()) {
                Optional<Producto> optionalProducto = ProductoRepository.findById(productoId);
                optionalProducto.ifPresent(productos::add);
            }
        }

        Producto productoGuardado = productoRepository.save(producto);
        return productoMapper.toDTOProducto(productoGuardado);
    }

    public void eliminarProducto(int id) {
        if(productoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("No hay productos");
        }
        productoRepository.deleteById(id);
    }


    public void actualizarProducto(int id, ProductoDTO productoDTO) {
        if(productoRepository.findById(id).isEmpty()) {
            throw new RuntimeException("No hay productos");
        }
        productoRepository.save(productoMapper.toEntity(productoDTO));
    }
}

//listo?