package com.Tienda.Franco.Controller;
import com.Tienda.Franco.DTO.PersonaDTO;
import com.Tienda.Franco.Service.PersonaServiceRest;
import com.Tienda.Franco.DTO.ApiresponseMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("api/Persona")
public class PersonaController {
    
    private final PersonaServiceRest personaService;

    public PersonaController(PersonaServiceRest personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiresponseMsg> agregarPersona(@RequestBody PersonaDTO personaDTO) {
        try {
            PersonaDTO personaCreada = personaService.guardarPersonaDTO(personaDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiresponseMsg("Persona creada exitosamente", HttpStatus.CREATED, personaCreada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiresponseMsg("Error al crear la persona: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiresponseMsg> obtenerPersonaAll() {
        try {
            List<PersonaDTO> personas = personaService.obtenerPersonaAll();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiresponseMsg("Lista de personas obtenida exitosamente", HttpStatus.OK, personas));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiresponseMsg("Error al obtener personas: " + e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiresponseMsg> obtenerPersonaPorId(@PathVariable int id) {
        try {
            PersonaDTO persona = personaService.obtenerPersonaPorId(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiresponseMsg("Persona obtenida exitosamente", HttpStatus.OK, persona));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiresponseMsg("Error: " + e.getMessage(), HttpStatus.NOT_FOUND));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiresponseMsg> borrarPersona(@PathVariable int id) {
        try {
            personaService.borrarPersona(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiresponseMsg("Persona eliminada correctamente", HttpStatus.OK));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiresponseMsg("Error al eliminar persona: " + e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiresponseMsg> actualizarPersona(@PathVariable int id, @RequestBody PersonaDTO personaDTO) {
        try {
            PersonaDTO personaActualizada = personaService.modificarPersona(id, personaDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiresponseMsg("Persona actualizada exitosamente", HttpStatus.OK, personaActualizada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiresponseMsg("Error al actualizar persona: " + e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }
}
