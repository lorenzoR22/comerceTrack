package com.example.comerceTrack.Producto.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoRequest {
    private String nombre;
    private BigDecimal precio;
    private Integer stock;
    private String descripcion;
    private String imagenUrl;
}
