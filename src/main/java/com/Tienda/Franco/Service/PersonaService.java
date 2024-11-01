package com.Tienda.Franco.Service;

import java.util.List;
import com.Tienda.Franco.DTO.PersonaDTO;

public interface PersonaService {

    PersonaDTO getPersonaById();

    List<PersonaDTO> obtenerPersonaAll();

    PersonaDTO guardarPersona(PersonaDTO personaDTO);

    PersonaDTO guardarPersonaFromAPI();

    PersonaDTO modificarPersona(PersonaDTO personaDTO);

    void borrarPersona(Long id);

}
//listo