package com.Tienda.Franco.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Tienda.Franco.Model.Producto;
import com.Tienda.Franco.Repository.ProductoRepository;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    public Producto obteneProductoPorId(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> obtenerProducto() {
    return productoRepository.findAll();
    }

    public Producto agregarUnProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

}