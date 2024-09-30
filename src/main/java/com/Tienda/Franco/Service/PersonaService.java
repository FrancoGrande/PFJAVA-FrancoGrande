package com.Tienda.Franco.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.Tienda.Franco.Model.Persona;
import com.Tienda.Franco.Repository.PersonaRepository;
import com.Tienda.Franco.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Persona guardarPersona(Persona persona) {
        return personaRepository.save(persona);
    }

    public List<Persona> obtenerPersona() {
        return personaRepository.findAll();
    }

    public Persona obtenerPersonaPorId(int id) {
        return personaRepository.findById(id).orElse(null);
    }

    public void eliminarPersona(int id) {
        personaRepository.deleteById(id);
    }

}
