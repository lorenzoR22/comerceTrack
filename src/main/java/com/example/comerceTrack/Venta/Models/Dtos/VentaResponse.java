package com.example.comerceTrack.Venta.Models.Dtos;

import java.math.BigDecimal;
import java.util.List;

public record VentaResponse(
        String fecha,
        BigDecimal total,
        Long usuario_id,
        List<DetalleVentaResponse> detalles){
}
