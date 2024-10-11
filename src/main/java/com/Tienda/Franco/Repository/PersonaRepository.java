package com.Tienda.Franco.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Tienda.Franco.Model.Persona;
import com.Tienda.Franco.Model.Producto;

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Integer> {

    

}
