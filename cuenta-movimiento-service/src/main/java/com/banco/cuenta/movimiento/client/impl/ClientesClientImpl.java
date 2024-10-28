package com.banco.cuenta.movimiento.client.impl;

import com.banco.cuenta.movimiento.client.ClientesClient;
import com.banco.cuenta.movimiento.model.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClientesClientImpl implements ClientesClient {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8081/clientes/";

    @Override
    public Optional<Cliente> obtenerCliente(Long clienteId) {
        log.info("Llamando al API de Clientes para el clienteId: {}", clienteId);
        try {
            return Optional.ofNullable(restTemplate.getForObject(BASE_URL + "{id}", Cliente.class, clienteId));
        } catch (Exception e) {
            log.error("Error al obtener el cliente: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener el cliente.");
        }
    }
}
