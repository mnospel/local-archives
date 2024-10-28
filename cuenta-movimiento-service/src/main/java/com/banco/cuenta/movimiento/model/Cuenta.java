package com.banco.cuenta.movimiento.model;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Cuenta {
    @Id
    @Column(name = "numero_cuenta")
    private String cuentaId = "";
    @Column(name = "clienteid")
    private Long clienteId;
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;
    @Column(name = "saldo_inicial")
    private Double saldoInicial;
    @Column(name = "estado")
    private Boolean estado;
}
