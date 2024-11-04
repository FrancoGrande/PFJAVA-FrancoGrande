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
    public static void main(String[] args) {
        SpringApplication.run(FrancoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {




    }

    
}