package com.Tienda.Franco.Model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import lombok.Data;
    
    
@Data   
@Entity
@NoArgsConstructor
@Table(name= "PRODUCTO")
public class Producto {
    
        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private long id;
    
        @Column(name ="NOMBRE")
        private String nombre;
    
        @Column(name ="COLOR")
        private String color;

        @Column(name ="PRECIO")
        private long precio;

        @ManyToOne(fetch = FetchType.LAZY)
        private Persona persona;

    }