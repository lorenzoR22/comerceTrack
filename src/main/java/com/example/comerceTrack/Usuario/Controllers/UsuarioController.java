package com.example.comerceTrack.Usuario.Controllers;

import com.example.comerceTrack.Usuario.Models.Dtos.UsuarioRequest;
import com.example.comerceTrack.Usuario.Models.Dtos.UsuarioResponse;
import com.example.comerceTrack.Usuario.Services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse saveUsuario(@RequestBody UsuarioRequest usuarioRequest){
        return usuarioService.saveUsuario(usuarioRequest);
    }

}
