package com.example.comerceTrack.Venta.Controllers;

import com.example.comerceTrack.Venta.Models.Dtos.VentaRequest;
import com.example.comerceTrack.Venta.Models.Dtos.VentaResponse;
import com.example.comerceTrack.Venta.Services.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
public class VentaController {
    private final VentaService ventaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponse saveVenta(@RequestBody VentaRequest ventaRequest){
        return ventaService.saveVenta(ventaRequest);
    }
}
