package com.Tienda.Franco.Controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Tienda.Franco.DTO.PersonaDTO;
import com.Tienda.Franco.Service.PersonaServiceRest;
import com.Tienda.Franco.DTO.ApiResponseMsg;



import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaServiceRest personaService;

    public PersonaController(PersonaServiceRest personaService) {
        this.personaService = personaService;
    }

    // Crear un nuevo persona a partir de un DTO
    @PostMapping("/create")
    public ResponseEntity<?> addPersona(@RequestBody PersonaDTO personaDTO) {
        try {
            PersonaDTO createdPersona = personaService.savePersonaFromApi(personaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseMsg("Persona creado", createdPersona));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al crear persona", e.getMessage()));
        }
    }

    // Obtener todos los personas
    @GetMapping("/all")
    public ResponseEntity<?> getAllPersonas() {
        try {
            List<PersonaDTO> personas = personaService.getAllPersonas();
            return ResponseEntity.ok(new ApiResponseMsg("Lista de personas", personas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("No hay personas", e.getMessage()));
        }
    }

    // Obtener un persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaById(@PathVariable Long id) {
        try {
            PersonaDTO persona = personaService.getPersonaById(id);
            return ResponseEntity.ok(new ApiResponseMsg("Persona encontrado", persona));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseMsg("Error: Persona no encontrado", e.getMessage()));
        }
    }

    // Eliminar persona por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable Long id) {
        try {
            personaService.deletePersona(id);
            return ResponseEntity.ok(new ApiResponseMsg("Persona eliminado", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: No se pudo eliminar el persona", e.getMessage()));
        }
    }

    // Actualizar un persona
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
        try {
            PersonaDTO updatedPersona = personaService.updatePersonaDTO(id, personaDTO);
            return ResponseEntity.ok(new ApiResponseMsg("Persona actualizado", updatedPersona));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al actualizar persona", e.getMessage()));
        }
    }
}