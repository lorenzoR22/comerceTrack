package com.example.comerceTrack.Venta.Models.Entities;

import com.example.comerceTrack.Usuario.Models.Entities.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha;

    private BigDecimal total=BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;//vendedor que hizo la venta

    @OneToMany(mappedBy = "venta",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta>detalles=new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }

    public void sumarAlTotal(BigDecimal precioAsumar){
        this.setTotal(this.getTotal().add(precioAsumar));
    }
}
