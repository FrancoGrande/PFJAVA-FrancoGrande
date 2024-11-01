package com.Tienda.Franco.Mapper;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.Tienda.Franco.DTO.PersonaDTO;
import com.Tienda.Franco.Model.Persona;


@Component
public class PersonaMapper {


    // Convertir de Persona a PersonaDTO
    public static PersonaDTO toDTOPersona(Persona persona) {

        if (persona == null) {
            throw new IllegalArgumentException("La persona no puede ser nula");
        }
        return PersonaDTO.builder()
                .id(persona.getId())
                .name(persona.getNombre())
                .dni(persona.getDni())
                .build();
    }

    
    // Convertir de PersonaDTO a Persona
    public static Persona toEntity(PersonaDTO personaDTO) {

        if (personaDTO == null) {
            throw new IllegalArgumentException("La personaDTO no puede ser nula");
        }

        Persona persona = new Persona();
        persona.setNombre(personaDTO.getName());
        persona.setDni(personaDTO.getDni());
        return persona;
    }
}


