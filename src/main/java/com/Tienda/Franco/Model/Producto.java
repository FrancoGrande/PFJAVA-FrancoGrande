package com.Tienda.Franco.Model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;



@Entity
@Data
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String tipo;

    private int stock; // Agregar atributo stock

    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;
 

    public Producto() {
    }

    public Producto(Long id, String nombre, String tipo, int stock, Persona persona) {	
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.stock = stock; // Manejo de stock
    }

    public int getStock() {
        return stock; // Método getter para stock
    }

    public void setStock(int stock) {
        this.stock = stock; // Método setter para stock
    }


}