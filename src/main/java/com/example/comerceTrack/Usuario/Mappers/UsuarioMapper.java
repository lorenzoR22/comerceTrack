package com.example.comerceTrack.Usuario.Mappers;

import com.example.comerceTrack.Usuario.Models.Dtos.UsuarioRequest;
import com.example.comerceTrack.Usuario.Models.Dtos.UsuarioResponse;
import com.example.comerceTrack.Usuario.Models.Entities.ERole;
import com.example.comerceTrack.Usuario.Models.Entities.Rol;
import com.example.comerceTrack.Usuario.Models.Entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "roles", source = "roles")
    @Mapping(target = "id",ignore = true)
    Usuario toEntity(UsuarioRequest usuarioRequest);

    @Mapping(target = "roles", source = "roles")
    UsuarioResponse toDTO(Usuario usuario);

    // Mapear String a Rol
    default Rol map(String nombreRol) {
        if (nombreRol == null) {
            return null;
        }
        Rol rol = new Rol();
        rol.setNombre(ERole.valueOf(nombreRol)); // o como tengas definido el enum
        return rol;
    }

    // Mapear Rol a String
    default String map(Rol rol) {
        if (rol == null) {
            return null;
        }
        return rol.getNombre().name();
    }

    // Mapear Set<String> a Set<Rol>
    default Set<Rol> map(Set<String> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }

    // Mapear Set<Rol> a Set<String>
    default Set<String> mapRoles(Set<Rol> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(this::map)
                .collect(Collectors.toSet());
    }
}
