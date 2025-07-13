package com.example.comerceTrack.Venta.Models.Dtos;

import java.util.List;

public record VentaRequest(
        Long usuario_id,
        List<DetalleVentaRequest> detalles){
}
