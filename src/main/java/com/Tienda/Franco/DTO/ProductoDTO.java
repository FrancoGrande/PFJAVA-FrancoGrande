package com.Tienda.Franco.DTO;

import java.util.Set;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDTO {
    @Schema(description = "Id del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "A15")
    private String nombre;

    @Schema(description = "Precio del producto", example = "350.0")
    private Double precio;

    @Schema(description = "Stock del producto", example = "15")
    private int stock;

    @Schema(description = "Categoría del producto", example = "celular")
    private String categoria;


    public ProductoDTO() {
    }

    public ProductoDTO(Long id, String nombre, Double precio, int stock, String categoria ) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

}