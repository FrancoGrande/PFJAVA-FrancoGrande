package com.Tienda.Franco.DTO;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class PersonaDTO {

    @Schema(description = "Identificador único persona", example = "1")
    private Long id;
    
    @Schema(description = "Nombre de la persona", example = " Lalo Landa")
    private String name;

    @Schema(description = "DNI de la persona", example = "123456") 
    private long dni;





    public PersonaDTO(Long id, String name, long dni) { 
        this.id = id;
        this.name = name;    
        this.dni = dni;    
    }





    public static Object builder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'builder'");
    }

}
//listo