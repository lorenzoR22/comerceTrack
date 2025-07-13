package com.example.comerceTrack.Venta.Mappers;

import com.example.comerceTrack.Venta.Models.Dtos.VentaResponse;
import com.example.comerceTrack.Venta.Models.Entities.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DetalleVentaMapper.class})
public interface VentaMapper {

    @Mapping(target = "usuario_id", source = "usuario.id")
    @Mapping(target = "detalles", source = "detalles")
    @Mapping(target = "fecha", source = "fecha",dateFormat = "yyyy-MM-dd HH:mm:ss")
    VentaResponse toDTO(Venta venta);


}
