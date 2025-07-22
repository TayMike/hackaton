package com.fiap.hackaton.usecase.paciente;

import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchPacienteUseCaseTest {

    private PacienteGateway pacienteGateway;
    private SearchPacienteUseCase useCase;

    @BeforeEach
    void setUp() {
        pacienteGateway = mock(PacienteGateway.class);
        useCase = new SearchPacienteUseCase(pacienteGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de pacientes quando existirem registros")
    void shouldReturnListOfPacientesWhenRecordsExist() {
        Paciente paciente = mock(Paciente.class);
        when(pacienteGateway.findAll()).thenReturn(List.of(paciente));

        List<Paciente> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(paciente, resultado.get(0));
        verify(pacienteGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de pacientes")
    void shouldReturnEmptyListWhenNoPacientesExist() {
        when(pacienteGateway.findAll()).thenReturn(Collections.emptyList());

        List<Paciente> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(pacienteGateway, times(1)).findAll();
    }
}