package com.banco.cuenta.movimiento.model.enums;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Generated
public enum TipoMovimiento {
    RETIRO("Retiro"),
    DEPOSITO("Depósito");

    private final String value;

    public static String getTipoMovimiento(double valor) {
        return valor < 0 ? RETIRO.value : DEPOSITO.value;
    }
}
