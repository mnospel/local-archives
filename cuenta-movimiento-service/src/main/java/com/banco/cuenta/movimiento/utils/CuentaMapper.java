package com.banco.cuenta.movimiento.utils;

import org.mapstruct.Mapper;
import com.banco.cuenta.movimiento.model.Cuenta;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuentaMapper {

    @Mapping(target = "cuentaId", source = "cuentaExistente.cuentaId")
    @Mapping(target = "clienteId", source = "cuentaExistente.clienteId")
    @Mapping(target = "tipoCuenta", source = "cuentaActualizada.tipoCuenta")
    @Mapping(target = "saldoInicial", source = "cuentaExistente.saldoInicial")
    @Mapping(target = "estado", source = "cuentaExistente.estado")
    Cuenta toCuenta(Cuenta cuentaExistente, Cuenta cuentaActualizada);
}
