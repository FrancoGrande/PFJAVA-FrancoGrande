package com.Tienda.Franco.Mapper;

import org.springframework.stereotype.Component;
import com.Tienda.Franco.DTO.PersonaDTO;
import com.Tienda.Franco.Model.Persona;

@Component
public class PersonaMapper {

    public PersonaDTO toDTOPersona(Persona persona) {
        if (persona == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }


        return PersonaDTO.builder()
                .id(persona.getId())
                .name(persona.getNombre())
                .email(persona.getEmail())
                .phone(persona.getPhone())
                .build();
    }

    public Persona toEntity(PersonaDTO personaDTO) {
        if (personaDTO == null) {
            throw new IllegalArgumentException("El personajeDTO no puede ser nulo");
        }

        Persona persona = new Persona();
        persona.setId(personaDTO.getId());
        persona.setNombre(personaDTO.getName());
        persona.setEmail(personaDTO.getEmail());
        persona.setPhone(personaDTO.getPhone());
        return persona;
    }
}