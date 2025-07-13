package com.example.comerceTrack.Usuario.Repositories;

import com.example.comerceTrack.Usuario.Models.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
