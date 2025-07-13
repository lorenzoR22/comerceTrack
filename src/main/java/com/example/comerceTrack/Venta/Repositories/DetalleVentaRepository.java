package com.example.comerceTrack.Venta.Repositories;

import com.example.comerceTrack.Venta.Models.Entities.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {
}
