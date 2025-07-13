package com.example.comerceTrack.Usuario.Models.Dtos;

import java.util.Set;

public record UsuarioRequest(
        String nombre,
        String username,
        String password,
        String mail,
        Set<String> roles){
}
