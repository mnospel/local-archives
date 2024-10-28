package com.banco.cliente;

import com.banco.cliente.exception.custom.ConflictException;
import com.banco.cliente.model.Cliente;
import com.banco.cliente.model.Persona;
import com.banco.cliente.repository.ClienteRepository;
import com.banco.cliente.repository.PersonaRepository;
import com.banco.cliente.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Persona persona;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        persona = new Persona();
        persona.setIdentificacion("123456");
        cliente = new Cliente();
        cliente.setPersona(persona);
    }

    @Test
    @DisplayName("Test crearCliente exitoso")
    void testCrearClienteExitoso() {
        when(personaRepository.findByIdentificacion(any())).thenReturn(null);
        when(personaRepository.save(cliente.getPersona())).thenReturn(persona);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente result = clienteService.crearCliente(cliente);
        assertNotNull(result);
        assertEquals(cliente, result);
        verify(personaRepository).save(cliente.getPersona());
        verify(clienteRepository).save(cliente);
    }

    @Test
    @DisplayName("Test crearCliente conflict")
    void testCrearClienteConflict() {
        when(personaRepository.findByIdentificacion(cliente.getPersona().getIdentificacion()))
                .thenReturn(persona);
        ConflictException exception = assertThrows(ConflictException.class, () -> {
            clienteService.crearCliente(cliente);
        });
        assertEquals("Ya existe el cliente registrado con la identificación.", exception.getMessage());
        verify(clienteRepository, never()).save(cliente);
    }

    @Test
    @DisplayName("Test crearCliente exception bd")
    void testCrearClienteExceptionBD() {
        when(personaRepository.findByIdentificacion(cliente.getPersona().getIdentificacion()))
                .thenThrow(new RuntimeException("Error en la base de datos"));
        Exception exception = assertThrows(RuntimeException.class, () -> {
            clienteService.crearCliente(cliente);
        });
        assertEquals("Error en la base de datos", exception.getMessage());
        verify(clienteRepository, never()).save(cliente);
    }
}

