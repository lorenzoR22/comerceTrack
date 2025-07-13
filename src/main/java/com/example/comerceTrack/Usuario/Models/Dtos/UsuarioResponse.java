package com.example.comerceTrack.Usuario.Models.Dtos;

import java.util.Set;

public record UsuarioResponse(
        String nombre,
        String username,
        String mail,
        Set<String> roles){
}
