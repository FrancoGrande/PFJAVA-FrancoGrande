package com.Tienda.Franco.Service;

import java.util.List;
import com.Tienda.Franco.DTO.PersonaDTO;


public interface PersonaService {
    PersonaDTO getPersonaById(Long id);

    List<PersonaDTO> getAllPersona();

    PersonaDTO savePersonaDTO(PersonaDTO personaDTO);

    PersonaDTO savePersonaFromApi(PersonaDTO personaDTO);

    void deletePersona(Long id);
}