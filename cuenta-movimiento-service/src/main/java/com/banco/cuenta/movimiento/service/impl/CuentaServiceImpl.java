package com.banco.cuenta.movimiento.service.impl;

import com.banco.cuenta.movimiento.client.ClientesClient;
import com.banco.cuenta.movimiento.model.Cuenta;
import com.banco.cuenta.movimiento.repository.CuentaRepository;
import com.banco.cuenta.movimiento.service.CuentaService;
import com.banco.cuenta.movimiento.utils.CuentaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;
    private final ClientesClient clientesClient;
    private final String ERROR_EXCEPTION_MESSAGE = "Error ocurred: {}";

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta obtenerCuenta(String numeroCuenta) {
        log.info("obtenerCuenta started");
        try {
            Optional<Cuenta> cuenta = cuentaRepository.findById(numeroCuenta);
            if (cuenta.isPresent()) {
                return cuenta.get();
            } else {
                log.debug("obtenerCuenta called");
                throw new RuntimeException("Cuenta no encontrada con ID: " + numeroCuenta);
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("obtenerCuenta completed");
        }
    }

    @Override
    public Cuenta actualizarCuenta(String numeroCuenta, Cuenta cuenta) {
        log.info("actualizarCliente started");
        try {
            Optional<Cuenta> cuentaExistente = cuentaRepository.findById(numeroCuenta);
            if (cuentaExistente.isPresent()) {
                return cuentaRepository.save(cuentaMapper.toCuenta(cuentaExistente.get(), cuenta));
            } else {
                log.debug("actualizarCliente called");
                throw new RuntimeException("Cliente no encontrado con ID: " + numeroCuenta);
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("actualizarCliente completed");
        }
    }

    @Override
    public void eliminarCuenta(String id) {
        log.info("eliminarCuenta started");
        try {
            if (cuentaRepository.existsById(id)) {
                cuentaRepository.deleteById(id);
            } else {
                log.debug("eliminarCuenta called");
                throw new RuntimeException("Cuenta no encontrada con ID: " + id);
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("eliminarCuenta completed");
        }

    }

    @Override
    public List<Cuenta> listarCuentas(Long clienteId) {
        log.info("listarCuentas started");
        try {
            if (clientesClient.obtenerCliente(clienteId).isPresent()) {
                return cuentaRepository.findAllByClienteId(clienteId);
            } else {
                log.debug("listarCuentas called");
                throw new RuntimeException("Cliente no encontrado con ID: " + clienteId);
            }
        }
        catch (Exception ex) {
            log.debug(ERROR_EXCEPTION_MESSAGE, ex.getMessage());
            throw ex;
        }
        finally {
            log.info("listarCuentas completed");
        }
    }
}
