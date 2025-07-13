package com.example.comerceTrack.Usuario.Repositories;

import com.example.comerceTrack.Usuario.Models.Entities.ERole;
import com.example.comerceTrack.Usuario.Models.Entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol,Long> {
    Optional<Rol>findByNombre(ERole nombre);
}
