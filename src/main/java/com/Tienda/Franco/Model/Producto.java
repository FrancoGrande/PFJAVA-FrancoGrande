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

    private Double precio; 
    private int stock; 

    @ManyToOne(fetch = FetchType.LAZY)
    private Persona persona;  // Relación con Persona

    // Constructor vacío
    public Producto() {
    }


    public Producto(Long id, String nombre, String tipo, Double precio, int stock, Persona persona) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo; 
        this.precio = precio; 
        this.stock = stock;
        this.persona = persona;
    }

    // Getters y setters para cada campo 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo; 
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio; 
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}