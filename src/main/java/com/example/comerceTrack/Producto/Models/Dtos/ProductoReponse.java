package com.example.comerceTrack.Producto.Models.Dtos;

import java.math.BigDecimal;

public record ProductoReponse(
        String nombre,
        BigDecimal precio,
        Integer stock,
        String descripcion,
        String imagenUrl
) {
}
