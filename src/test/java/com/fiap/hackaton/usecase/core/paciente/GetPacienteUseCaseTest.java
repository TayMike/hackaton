package com.fiap.hackaton.usecase.core.paciente;

import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
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
        Paciente paciente = new Paciente(
                "12345678900",
                "João da Silva",
                OffsetDateTime.now(),
                "01234567",
                100
        );

        when(pacienteGateway.findById(id)).thenReturn(Optional.of(paciente));

        Paciente resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(paciente.getCpf(), resultado.getCpf());
        assertEquals(paciente.getNome(), resultado.getNome());
        assertEquals(paciente.getCep(), resultado.getCep());
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