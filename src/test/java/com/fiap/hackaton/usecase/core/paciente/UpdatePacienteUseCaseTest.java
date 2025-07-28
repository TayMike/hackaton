package com.fiap.hackaton.usecase.core.paciente;

import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.usecase.core.paciente.dto.IPacienteUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdatePacienteUseCaseTest {

    private PacienteGateway pacienteGateway;
    private UpdatePacienteUseCase useCase;

    @BeforeEach
    void setUp() {
        pacienteGateway = mock(PacienteGateway.class);
        useCase = new UpdatePacienteUseCase(pacienteGateway);
    }

    @Test
    @DisplayName("Deve atualizar todos os campos do paciente quando dados válidos são fornecidos")
    void shouldUpdateAllFieldsWhenValidDataProvided() throws PacienteNotFoundException {
        UUID id = UUID.randomUUID();
        IPacienteUpdateData dados = mock(IPacienteUpdateData.class);
        Paciente paciente = mock(Paciente.class);

        when(pacienteGateway.findById(id)).thenReturn(Optional.of(paciente));
        when(pacienteGateway.update(paciente)).thenReturn(paciente);

        when(dados.nome()).thenReturn("Carlos");
        when(dados.cep()).thenReturn("12345678");
        when(dados.numeroCasa()).thenReturn(99);

        Paciente resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        assertEquals(paciente, resultado);
        verify(paciente).setNome("Carlos");
        verify(paciente).setCep("12345678");
        verify(paciente).setNumeroCasa(99);
        verify(pacienteGateway, times(1)).update(paciente);
    }

    @Test
    @DisplayName("Deve lançar PacienteNotFoundException quando paciente não existe")
    void shouldThrowPacienteNotFoundExceptionWhenPacienteDoesNotExist() {
        UUID id = UUID.randomUUID();
        IPacienteUpdateData dados = mock(IPacienteUpdateData.class);

        when(pacienteGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(PacienteNotFoundException.class, () -> useCase.execute(id, dados));
        verify(pacienteGateway, times(1)).findById(id);
        verify(pacienteGateway, never()).update(any());
    }

    @Test
    @DisplayName("Deve ignorar atualização de campos quando valores nulos ou em branco são fornecidos")
    void shouldIgnoreUpdateWhenFieldsAreNullOrBlank() throws PacienteNotFoundException {
        UUID id = UUID.randomUUID();
        IPacienteUpdateData dados = mock(IPacienteUpdateData.class);
        Paciente paciente = mock(Paciente.class);

        when(pacienteGateway.findById(id)).thenReturn(Optional.of(paciente));
        when(pacienteGateway.update(paciente)).thenReturn(paciente);

        when(dados.nome()).thenReturn(" ");
        when(dados.cep()).thenReturn(null);
        when(dados.numeroCasa()).thenReturn(null);

        Paciente resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(paciente, never()).setNome(any());
        verify(paciente, never()).setCep(any());
        verify(paciente, never()).setNumeroCasa(any());
        verify(pacienteGateway, times(1)).update(paciente);
    }
}