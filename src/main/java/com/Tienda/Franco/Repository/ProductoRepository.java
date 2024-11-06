package com.Tienda.Franco.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tienda.Franco.Model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}