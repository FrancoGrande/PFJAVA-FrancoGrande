package com.Tienda.Franco.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Tienda.Franco.DTO.PersonaDTO;
import com.Tienda.Franco.Mapper.PersonaMapper;
import com.Tienda.Franco.Model.Persona;
import com.Tienda.Franco.Model.Producto;
import com.Tienda.Franco.Repository.PersonaRepository;
import com.Tienda.Franco.Repository.ProductoRepository;

import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonaServiceRest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private RestTemplate restTemplate;

    public PersonaServiceRest(PersonaRepository personaRepository, PersonaMapper personaMapper) {
        this.personaRepository = personaRepository;
        this.personaMapper = personaMapper;
    }

    public List<PersonaDTO> obtenerPersonaAll() {
        List<PersonaDTO> personaDB = personaRepository.findAll().stream()
                .map(personaMapper::toDTOPersona)
                .collect(Collectors.toList());

        Persona[] personaAPI = restTemplate.getForObject(BASE_URL, Persona[].class);

        if (personaAPI != null) {
            for (Persona persona : personaAPI) {
                personaDB.add(personaMapper.toDTOPersona(persona));
            }
        }

        return personaDB;
    }

    public PersonaDTO obtenerPersonaPorId(Long id) {
        Optional<Persona> optionalPersona = personaRepository.findById(id);

        if (optionalPersona.isPresent()) {
            return personaMapper.toDTOPersona(optionalPersona.get());
        } else {
            PersonaDTO personaDTO = restTemplate.getForObject(BASE_URL + "/{id}", PersonaDTO.class, id);
            if (personaDTO != null) {
                return personaDTO;
            } else {
                throw new IllegalArgumentException("Persona no encontrada");
            }
        }
    }

    @Transactional
    public PersonaDTO guardarPersonaFromAPI(long id) {
        PersonaDTO personaDTO = restTemplate.getForObject(BASE_URL + "/{id}", PersonaDTO.class, id);

        if (personaDTO != null) {
            Persona persona = personaMapper.toEntity(personaDTO);
            Persona personaGuardada = personaRepository.save(persona);
            return personaMapper.toDTOPersona(personaGuardada);
        } else {
            throw new IllegalArgumentException("Persona no guardada");
        }
    }

    public PersonaDTO guardarPersonaDTO(PersonaDTO personaDTO) {
        Persona persona = personaMapper.toEntity(personaDTO);

        if (personaDTO.getProductoIds() != null && !personaDTO.getProductoIds().isEmpty()) {
            Set<Producto> productos = new HashSet<>();

            for (long productoId : personaDTO.getProductoIds()) {
                Optional<Producto> optionalProducto = productoRepository.findById(productoId);
                optionalProducto.ifPresent(productos::add);
            }

            persona.setProductos(productos);  // Asegúrate de que Persona tenga este atributo y método
        }

        Persona personaGuardada = personaRepository.save(persona);
        return personaMapper.toDTOPersona(personaGuardada);
    }

    public void borrarPersona(Long id) {
        if (personaRepository.existsById(id)) {
            personaRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Persona no encontrada");
        }
    }

    public PersonaDTO modificarPersona(Long id, PersonaDTO personaDTO) {
        return personaRepository.findByDni(id)
                .map(persona -> {
                    persona.setName(personaDTO.getName());
                    persona.setDni(personaDTO.getDni());
                    return personaMapper.toDTOPersona(personaRepository.save(persona));
                })
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));
    }
}