package com.Tienda.Franco.Controller;
import java.util.List;
import java.lang.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.Tienda.Franco.Model.Persona;
import com.Tienda.Franco.Service.PersonaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/Persona")
public class PersonaController {
    
    @Autowired
    private PersonaService personaService;
    
    @GetMapping("/all")
    public List<Persona> obtenerPersonaPorId() {
        return this.personaService.obtenerPersona();

    }
    

    @PostMapping("/create")
    public ResponseEntity<?> agregarUnaPersona(@RequestBody Persona persona) {
        
        try{
            this.personaService.agregarUnaPersona(persona);
            return ResponseEntity.ok(persona);
            
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            return ResponseEntity.status(400).body(e.getMessage());
        }

    
    }
}
