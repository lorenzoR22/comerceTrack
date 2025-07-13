package com.example.comerceTrack.Producto.Controllers;

import com.example.comerceTrack.Producto.Models.Dtos.ProductoReponse;
import com.example.comerceTrack.Producto.Services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    
    private final ProductoService productoService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoReponse getProducto(@PathVariable("id")Long id){
        return productoService.getProducto(id);
    }

    @PostMapping("/importar")
    @ResponseStatus(HttpStatus.CREATED)
    public void importarProductos(@RequestParam("file")MultipartFile file) throws Exception {
        productoService.lanzarJobConArchivo(file);
    }
}
