package com.Tienda.Franco.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.Tienda.Franco.DTO.PersonaDTO;
import com.Tienda.Franco.Mapper.PersonaMapper;
import com.Tienda.Franco.Model.Persona;
import com.Tienda.Franco.Model.Producto;
import com.Tienda.Franco.Repository.PersonaRepository;
import com.Tienda.Franco.Repository.ProductoRepository;

import jakarta.transaction.Transactional;
import scala.collection.mutable.HashSet;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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


    public List<PersonaDTO> obtenerPersonaAll() {
        List<PersonaDTO> personaDB = personaRepository.findAll().stream()
                .map(personaMapper::toDTOPersona).toList();
                .collect(Collectors.toList());

                Persona[] personaAPI =restTemplate.getForObject(BASE_URL,Persona[].class);

                if (personaAPI != null) {
                    for (Persona persona : personaAPI) {
                        personaDB.add(PersonaMapper.toDTOPersona(persona));
                    }
                }

                return personaDB;
        
    }


    public PersonaDTO obtenerPersonaPorId(Long id) {
        Optional<Persona> optionalPersona = personaRepository.findById(id); 

        if(!optionalPersona.isPresent()) {
            return personaMapper.toDTOPersona(optionalPersona.get());
        } else {
            PersonaDTO personaDTO = restTemplate.getForObject(BASE_URL + "/{id}", PersonaDTO.class, id);
            if(personaDTO != null) {
                return personaDTO;
            } else {
                throw new IllegalArgumentException("Persona no encontrada");
            }
        }
            
    }
    @Transactional
    public PersonaDTO guardarPersonaFromAPI(long id) {
        PersonaDTO personaDTO = restTemplate.getForObject(BASE_URL +"/{id}", PersonaDTO.class, id);

        if(personaDTO != null) {
            Persona persona = PersonaMapper.toEntity(personaDTO);
            Persona personaGuardada = personaRepository.save(persona);
            return personaMapper.toDTOPersona(personaGuardada);
        } else {
            throw new IllegalArgumentException("Persona no guardada");
        }
    }

    public PersonaDTO guardarPersonaDTO(PersonaDTO personaDTO) {
        Persona persona = PersonaMapper.toEntity(personaDTO);

        if(personaDTO.getProductoIds() != null && !personaDTO.getProductoIds().isEmpty()) {
            List<Producto> productos = new HashSet<>();


            for (long productoId : personaDTO.getProductoIds()) {
                Optional<Producto> optionalProducto = ProductoRepository.findById(productoId);
                optionalProducto.ifPresent(productos::add);
            }
        }

        Persona personaGuardada = personaRepository.save(persona);
        return personaMapper.toDTOPersona(personaGuardada);
    }

    public void borrarPersona(Long id) {
        if(personaRepository.existsById(id)) {
            personaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Persona no encontrada");
        }
    }

    public PersonaDTO modificarPersona(Long id, PersonaDTO personaDTO) {
        return personaRepository.findById(id)
                .map(persona -> {
                    persona.setName(personaDTO.getName());
                    persona.setDni(personaDTO.getDni());
                    return personaMapper.toDTOPersona(personaRepository.save(persona));
                })
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));

        }

    public personaDTO guardarPersonaFromAPI(PersonaDTO personaDTO) {
        throw new UnsupportedOperationException("Unimplemented method 'guardarPersonaFromAPI'");
    }

}
