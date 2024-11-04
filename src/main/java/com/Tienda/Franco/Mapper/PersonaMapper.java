package com.Tienda.Franco.Mapper;

import org.springframework.stereotype.Component;

import com.Tienda.Franco.DTO.PersonaDTO;
import com.Tienda.Franco.Model.Persona;

import lombok.Builder;


@Component
public class PersonaMapper {

    // Convertir de Persona a PersonaDTO
    public PersonaDTO toDTOPersona(Persona persona) {
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
    public Persona toEntity(PersonaDTO personaDTO) {
        if (personaDTO == null) {
            throw new IllegalArgumentException("La personaDTO no puede ser nula");
        }

        Persona persona = new Persona();
        persona.setNombre(personaDTO.getName());
        persona.setDni(personaDTO.getDni());
        return persona;
    }
}