package com.example.comerceTrack.Usuario.Services;

import com.example.comerceTrack.Usuario.Mappers.UsuarioMapper;
import com.example.comerceTrack.Usuario.Models.Dtos.UsuarioRequest;
import com.example.comerceTrack.Usuario.Models.Dtos.UsuarioResponse;
import com.example.comerceTrack.Usuario.Models.Entities.Usuario;
import com.example.comerceTrack.Usuario.Repositories.RolRepository;
import com.example.comerceTrack.Usuario.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioResponse getUsuario(Long id){
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()->new RuntimeException("no se encontro el user"));
        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioResponse saveUsuario(UsuarioRequest usuarioRequest){
        Usuario usuario=usuarioMapper.toEntity(usuarioRequest);
        Usuario saved=usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(saved);
    }
}
