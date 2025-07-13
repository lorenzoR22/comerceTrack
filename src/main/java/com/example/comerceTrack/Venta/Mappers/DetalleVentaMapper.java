package com.example.comerceTrack.Venta.Mappers;

import com.example.comerceTrack.Venta.Models.Dtos.DetalleVentaResponse;
import com.example.comerceTrack.Venta.Models.Entities.DetalleVenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper {

    @Mapping(target = "venta_id",source = "venta.id")
    @Mapping(target = "producto_id",source = "producto.id")
    DetalleVentaResponse toDTO(DetalleVenta detalleVenta);

}
