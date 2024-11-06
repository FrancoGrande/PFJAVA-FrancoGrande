package com.Tienda.Franco.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Tienda.Franco.DTO.PersonaDTO;
import com.Tienda.Franco.Model.Persona;
import com.Tienda.Franco.Repository.PersonaRepository;
import com.Tienda.Franco.Mapper.PersonaMapper;

import jakarta.transaction.Transactional;

@Service
public class PersonaServiceRest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    @Autowired
    private final PersonaRepository personaRepository;
    @Autowired
    private final PersonaMapper personaMapper;
    @Autowired
    private RestTemplate restTemplate;

    public PersonaServiceRest(PersonaRepository personaRepository, PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
    }

    public List<PersonaDTO> getAllPersonas() {
        List<PersonaDTO> personasDB = personaRepository.findAll().stream()
                .map(personaMapper::toDTOPersona)
                .collect(Collectors.toList());

        Persona[] personasAPI = restTemplate.getForObject(BASE_URL, Persona[].class);

        if (personasAPI != null) {
            for (Persona persona : personasAPI) {
                personasDB.add(personaMapper.toDTOPersona(persona));
            }
        }

        return personasDB;
    }

    public PersonaDTO getPersonaById(Long id) {
        Optional<Persona> optionalPersona = personaRepository.findById(id);

        if (optionalPersona.isPresent()) {
            return personaMapper.toDTOPersona(optionalPersona.get());
        } else {
            // Si el persona no está en la base de datos, buscar en la API externa
            PersonaDTO personaDTO = restTemplate.getForObject(BASE_URL + "/{id}", PersonaDTO.class, id);
            if (personaDTO != null) {
                return personaDTO;
            } else {
                throw new RuntimeException("Persona no encontrado ni en la base de datos ni en la API externa");
            }
        }
    }

    @Transactional
    public PersonaDTO savePersonaFromApi(Long id) {
        PersonaDTO personaDTO = restTemplate.getForObject(BASE_URL + "/{id}", PersonaDTO.class, id);

        if (personaDTO != null) {
            Persona persona = personaMapper.toEntity(personaDTO);
            Persona savedPersona = personaRepository.save(persona);
            return personaMapper.toDTOPersona(savedPersona);
        } else {
            throw new RuntimeException("Persona no encontrado en la API con ID: " + id);
        }
    }

    public PersonaDTO savePersonaDTO(PersonaDTO personaDTO) {
        // Crear un nuevo persona a partir del DTO
        Persona persona = personaMapper.toEntity(personaDTO);


        // Guarda el persona en la base de datos
        Persona savedPersona = personaRepository.save(persona);
        // Retorna el DTO del persona guardado
        return personaMapper.toDTOPersona(savedPersona);
    }

    // Eliminar un persona en la base de datos y en la API externa
    public void deletePersona(Long id) {
        if (personaRepository.existsById(id)) {
            personaRepository.deleteById(id);
            // Eliminar el persona en la API externa
            restTemplate.delete(BASE_URL + "/{id}", id);
        } else {
            throw new RuntimeException("Persona no encontrado con ID: " + id);
        }
    }

    public PersonaDTO updatePersonaDTO(Long id, PersonaDTO personaDTO) {
        return personaRepository.findById(id)
            .map(persona -> {
                persona.setNombre(personaDTO.getName());;
                return personaMapper.toDTOPersona(personaRepository.save(persona));
            })
            .orElse(null);
    }

    public PersonaDTO savePersonaFromApi(PersonaDTO personaDTO) {
        throw new UnsupportedOperationException("Unimplemented method 'savePersonaFromApi'");
    }
}