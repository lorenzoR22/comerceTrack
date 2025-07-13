package com.example.comerceTrack.Processor;

import com.example.comerceTrack.Producto.Models.Dtos.ProductoRequest;
import com.example.comerceTrack.Producto.Models.Entities.Producto;
import org.springframework.batch.item.ItemProcessor;

public class ProductoItemProcessor implements ItemProcessor<ProductoRequest, Producto> {

    @Override
    public Producto process(ProductoRequest input) {
        Producto producto=new Producto();
        producto.setNombre(input.getNombre());
        producto.setDescripcion(input.getDescripcion());
        producto.setStock(input.getStock());
        producto.setPrecio(input.getPrecio());
        producto.setImagenUrl(input.getImagenUrl());
        return producto;
    }
}
