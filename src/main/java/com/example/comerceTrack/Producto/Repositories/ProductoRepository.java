package com.example.comerceTrack.Producto.Repositories;

import com.example.comerceTrack.Producto.Models.Entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
