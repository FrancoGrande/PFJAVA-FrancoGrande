package com.Tienda.Franco.Model;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
    
    
@Data   
@Entity
@NoArgsConstructor
@Table(name= "PRODUCTO")
public class Producto {
    
        @Id
        @GeneratedValue( strategy = GenerationType.IDENTITY)
        private int id;
    
        @Column(name ="NOMBRE")
        private String nombre;
    
        @Column(name ="COLOR")
        private String color;

        @Column(name ="PRECIO")
        private int precio;

        @ManyToOne(fetch = FetchType.LAZY)
        private Persona persona;


        
    }
