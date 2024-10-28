package com.banco.cuenta.movimiento.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Movimientos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Long movimientoId;
    @Column(name = "fecha")
    private LocalDateTime fecha;
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;
    @Column(name = "valor")
    private double valor;
    @Column(name = "saldo")
    private double saldo;
    @ManyToOne
    @JoinColumn(name = "numero_cuenta", referencedColumnName = "numero_cuenta", nullable = false)
    private Cuenta cuenta;
}
