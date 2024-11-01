package com.Tienda.Franco.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;




@Data
@Builder

public class ProductoDTO {

    @Schema(description = "Identificador único persona", example = "1")
    private Long id;

    @Schema(description = "color del equiop", example = "rojo")
    private String color;
    
    @Schema(description = "Nombre de la persona", example = " A15")
    private String name;

    @Schema(description = "precio del equipo", example = "15000") 
    private long precio;

    public ProductoDTO(Long id, String color, String name, long precio) {
        this.id = id;
        this.color = color;
        this.name = name;
        this.precio = precio;
    }

}

// listo