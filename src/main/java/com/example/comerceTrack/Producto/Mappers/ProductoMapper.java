package com.example.comerceTrack.Producto.Mappers;

import com.example.comerceTrack.Producto.Models.Dtos.ProductoReponse;
import com.example.comerceTrack.Producto.Models.Dtos.ProductoRequest;
import com.example.comerceTrack.Producto.Models.Entities.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "id", ignore = true)
    Producto toEntity(ProductoRequest productoRequest);

    ProductoReponse toDTO(Producto producto);
}
