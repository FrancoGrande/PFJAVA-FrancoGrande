package com.Tienda.Franco.Controller;
import com.Tienda.Franco.DTO.PersonaDTO;
import com.Tienda.Franco.Model.Persona;
import com.Tienda.Franco.Service.PersonaService;
import com.Tienda.Franco.Service.PersonaServiceRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("api/Persona")
public class PersonaController {
    

    private final PersonaServiceRest personaService;

    public PersonaController(PersonaServiceRest personaService) {
        this.personaService = personaService;
    }
    @PostMapping("/create")
    public ResponseEntity<?>agregarPersona(@RequestBody PersonaDTO personaDTO) {

        try{
            PersonaDTO personaCreada = personaService.guardarPersonaDTO(personaDTO);        
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiresponseMsg());

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());  
        }
    }

    @GetMapping("/all") 
    public ResponseEntity<?> obtenerPersonaAll() {
        try {
            List<PersonaDTO> persona = personaService.obtenerPersonaAll(); 
            return ResponseEntity.status(HttpStatus.OK).body(persona);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPersonaPorId(@PathVariable Long id) {
        try {
            PersonaDTO persona = personaService.obtenerPersonaPorId(id); 
            return ResponseEntity.status(HttpStatus.OK).body(persona);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> borrarPersona(@PathVariable Long id) {
        try {
            personaService.borrarPersona(id); 
            return ResponseEntity.status(HttpStatus.OK).body("Persona eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
        try {
            PersonaDTO personaActualizada = personaService.modificarPersona(id, personaDTO); 
            return ResponseEntity.status(HttpStatus.OK).body(personaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    
}
