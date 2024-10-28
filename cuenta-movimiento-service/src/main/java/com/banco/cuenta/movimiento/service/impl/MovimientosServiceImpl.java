package com.banco.cuenta.movimiento.service.impl;

import com.banco.cuenta.movimiento.client.ClientesClient;
import com.banco.cuenta.movimiento.exception.custom.ConflictException;
import com.banco.cuenta.movimiento.model.Cliente;
import com.banco.cuenta.movimiento.model.Cuenta;
import com.banco.cuenta.movimiento.model.Movimientos;
import com.banco.cuenta.movimiento.model.dto.ReporteMovimientos;
import com.banco.cuenta.movimiento.model.enums.TipoMovimiento;
import com.banco.cuenta.movimiento.repository.CuentaRepository;
import com.banco.cuenta.movimiento.repository.MovimientosRepository;
import com.banco.cuenta.movimiento.service.MovimientosService;
import com.banco.cuenta.movimiento.utils.MovimientosMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovimientosServiceImpl implements MovimientosService {

    @Autowired
    private MovimientosRepository movimientosRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    private final ClientesClient clientesClient;
    private final MovimientosMapper movimientosMapper;
    private final String ERROR_EXCEPTION_MESSAGE = "Error ocurred: {}";

    @Override
    public Movimientos registrarMovimiento(String numeroCuenta, Movimientos movimiento) {
        log.info("registrarMovimiento started");
        try {
            Optional<Cuenta> cuenta = cuentaRepository.findById(numeroCuenta);
            if (cuenta.isPresent()) {
                Cuenta cuentaExistente = cuenta.get();
                double nuevoSaldo = cuentaExistente.getSaldoInicial() + movimiento.getValor();
                if (nuevoSaldo < 0) {
                    throw new ConflictException("Saldo no disponible.");
                }
                movimiento.setTipoMovimiento(TipoMovimiento.getTipoMovimiento(movimiento.getValor()));
                movimiento.setFecha(LocalDateTime.now());
                movimiento.setSaldo(cuentaExistente.getSaldoInicial());
                cuentaExistente.setSaldoInicial(nuevoSaldo);
                movimiento.setCuenta(cuentaExistente);
                log.info("cuentaExistente: {}", cuentaExistente);
                log.info("movimiento: {}", movimiento);
                cuentaRepository.save(cuentaExistente);
                return movimientosRepository.save(movimiento);
            }
            else {
                throw new RuntimeException("Cuenta no existente.");
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("registrarMovimiento completed");
        }
    }

    @Override
    public List<Movimientos> obtenerMovimientosPorCuenta(String numeroCuenta) {
        log.info("obtenerMovimientosPorCuenta started");
        try {
            if(cuentaRepository.existsById(numeroCuenta)) {
                return movimientosRepository.findByCuentaCuentaId(numeroCuenta);
            }
            else {
                throw new RuntimeException("Cuenta no existente.");
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("obtenerMovimientosPorCuenta completed");
        }
    }

    @Override
    public List<ReporteMovimientos> generarReporte(LocalDateTime fechaInicio, LocalDateTime fechaFin, Long clienteId) {
        log.info("generarReporte started");
        try {
            Optional<Cliente> cliente = clientesClient.obtenerCliente(clienteId);
            if (cliente.isPresent()) {
                List<String> cuentaIds = cuentaRepository.findAllByClienteId(clienteId).stream().map(Cuenta::getCuentaId).toList();
                List<Movimientos> movimientos = movimientosRepository.findByFechaBetweenAndCuentaCuentaIdIn(fechaInicio, fechaFin, cuentaIds);
                return movimientos.stream().map(movimiento -> {
                    return movimientosMapper.toReporteMovimientos(movimiento, cliente.get().getPersona());
                }).collect(Collectors.toList());
            }
            else {
                throw new RuntimeException("Cliente no encontrado con ID: " + clienteId);
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("generarReporte completed");
        }
    }
}
