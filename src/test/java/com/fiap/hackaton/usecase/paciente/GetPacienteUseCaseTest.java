package com.fiap.hackaton.usecase.paciente;

import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetPacienteUseCaseTest {

    private PacienteGateway pacienteGateway;
    private GetPacienteUseCase useCase;

    @BeforeEach
    void setUp() {
        pacienteGateway = mock(PacienteGateway.class);
        useCase = new GetPacienteUseCase(pacienteGateway);
    }

    @Test
    @DisplayName("Deve retornar paciente quando encontrado pelo id")
    void shouldReturnPacienteWhenFoundById() throws PacienteNotFoundException {
        UUID id = UUID.randomUUID();
        PacienteSchema pacienteSchema = mock(PacienteSchema.class);
        Paciente pacienteRetornado = mock(Paciente.class);

        when(pacienteGateway.findById(id)).thenReturn(Optional.of(pacienteSchema));
        when(pacienteSchema.toPaciente()).thenReturn(pacienteRetornado);

        Paciente resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(pacienteRetornado, resultado);
        verify(pacienteGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar PacienteNotFoundException quando paciente não existe")
    void shouldThrowPacienteNotFoundExceptionWhenPacienteDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(pacienteGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(PacienteNotFoundException.class, () -> useCase.execute(id));
        verify(pacienteGateway, times(1)).findById(id);
    }
}