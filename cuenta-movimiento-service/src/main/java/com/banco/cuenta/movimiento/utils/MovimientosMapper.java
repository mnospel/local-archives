package com.banco.cuenta.movimiento.utils;

import com.banco.cuenta.movimiento.model.Movimientos;
import com.banco.cuenta.movimiento.model.Persona;
import com.banco.cuenta.movimiento.model.dto.ReporteMovimientos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovimientosMapper {
    @Mapping(target = "fechaMovimiento", source = "movimiento.fecha")
    @Mapping(target = "nombreCliente", source = "persona.nombre")
    @Mapping(target = "numeroCuenta", source = "movimiento.cuenta.cuentaId")
    @Mapping(target = "tipoMovimiento", source = "movimiento.tipoMovimiento")
    @Mapping(target = "saldoMovimiento", source = "movimiento.saldo")
    @Mapping(target = "estadoCuenta", source = "movimiento.cuenta.estado")
    @Mapping(target = "valorMovimiento", source = "movimiento.valor")
    @Mapping(target = "saldoDisponible", source = "movimiento.cuenta.saldoInicial")
    ReporteMovimientos toReporteMovimientos(Movimientos movimiento, Persona persona);
}
