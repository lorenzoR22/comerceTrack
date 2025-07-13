package com.example.comerceTrack.Venta.Models.Dtos;

public record DetalleVentaRequest(
        Long producto_id,
        Integer cantidad
) {
}
