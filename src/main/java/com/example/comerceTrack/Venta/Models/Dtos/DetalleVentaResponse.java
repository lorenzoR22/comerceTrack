package com.example.comerceTrack.Venta.Models.Dtos;

public record DetalleVentaResponse(
        Long venta_id,
        Long producto_id,
        Integer cantidad
) {
}
