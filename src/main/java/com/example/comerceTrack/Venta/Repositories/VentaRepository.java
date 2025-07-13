package com.example.comerceTrack.Venta.Repositories;

import com.example.comerceTrack.Venta.Models.Entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta,Long> {
}
