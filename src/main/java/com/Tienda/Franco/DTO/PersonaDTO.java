package com.Tienda.Franco.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonaDTO {
    @Schema(description = "Identificador de Persona", example = "1")
    private Long id;

    @Schema(description = "Nombre del Persona", example = "Asdasd Asdasd")
    private String name;
    
    @Schema(description = "Email del Persona", example = "asdasd@example.com")
    private String email;
    
    @Schema(description = "Número de teléfono del Persona", example = "1234567890")
    private String phone;
    


    public PersonaDTO() {
    }

    public PersonaDTO(Long id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

}