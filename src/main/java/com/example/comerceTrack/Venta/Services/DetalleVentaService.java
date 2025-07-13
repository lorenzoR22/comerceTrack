package com.example.comerceTrack.Venta.Services;

import com.example.comerceTrack.Venta.Mappers.DetalleVentaMapper;
import com.example.comerceTrack.Venta.Models.Dtos.DetalleVentaRequest;
import com.example.comerceTrack.Venta.Models.Dtos.DetalleVentaResponse;
import com.example.comerceTrack.Venta.Models.Entities.DetalleVenta;
import com.example.comerceTrack.Venta.Repositories.DetalleVentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetalleVentaService {
    private final DetalleVentaRepository detalleVentaRepository;
    private final DetalleVentaMapper detalleVentaMapper;

    public DetalleVentaResponse getDetalleVenta(Long id){
        DetalleVenta item=detalleVentaRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No se encontro el item"));
        return detalleVentaMapper.toDTO(item);
    }
}
