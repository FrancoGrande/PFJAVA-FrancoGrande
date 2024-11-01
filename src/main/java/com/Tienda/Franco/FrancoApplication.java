package com.Tienda.Franco;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import com.Tienda.Franco.Service.ProductoService;
import com.Tienda.Franco.Service.PersonaServiceRest;
import com.Tienda.Franco.Model.Persona;
import com.Tienda.Franco.Model.Producto;



@SpringBootApplication
public class FrancoApplication implements CommandLineRunner {
    @Autowired
    private PersonaServiceRest personaService;
    @Autowired
    private ProductoService productoService;

    public static void main(String[] args) {
        SpringApplication.run(FrancoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //creamos personas
        Persona persona1= new Persona();
        persona1.setNombre("Franco");
        persona1.setDni(35099580);

        Persona persona2= new Persona();
        persona2.setNombre("Rocio");
        persona2.setDni(36760701);

        //guardamos personas en la base de datos
        personaService.agregarUnaPersona(persona1);
        personaService.agregarUnaPersona(persona2);


        //creamos productos
        Producto celu1= new Producto();
        celu1.setNombre("Xiaomi A3");
        celu1.setColor("negro");
        celu1.setPrecio(4500);

        Producto celu2= new Producto();
        celu2.setNombre("Samsung A15");
        celu2.setColor("amarillo");
        celu2.setPrecio(5000);

        //guardamos celulares en la base de datos
        productoService.agregarUnProducto(celu1);
        productoService.agregarUnProducto(celu2);

        //buscar personas
        personaService.obtenerPersona();

        // System.out.println(persona1.getNombre() + " compro un " + celu2.getNombre() + "al precio de $" + celu2.getPrecio());
    }

    
}