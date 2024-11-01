package com.Tienda.Franco.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Tienda.Franco.Model.Persona;  

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Integer> {

    boolean existsByDni (long dni);

    List<Persona> findByDni (long dni);

    List<Persona> findByNombre (Persona persona);
}
