package com.fiap.hackaton.usecase.paciente;

import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.paciente.dto.IPacienteRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreatePacienteUseCaseTest {

    private PacienteGateway pacienteGateway;
    private CreatePacienteUseCase useCase;

    @BeforeEach
    void setUp() {
        pacienteGateway = mock(PacienteGateway.class);
        useCase = new CreatePacienteUseCase(pacienteGateway);
    }

    @Test
    @DisplayName("Deve criar paciente com dados válidos")
    void shouldCreatePacienteWithValidData() {
        IPacienteRegistrationData dados = mock(IPacienteRegistrationData.class);
        Paciente paciente = mock(Paciente.class);

        var now = OffsetDateTime.now();

        when(dados.cpf()).thenReturn("12345678900");
        when(dados.nome()).thenReturn("João Sil va");
        when(dados.primeiroDiaCadastro()).thenReturn(now);
        when(dados.cep()).thenReturn("01234567");
        when(dados.numeroCasa()).thenReturn(100);
        when(pacienteGateway.save(any(Paciente.class))).thenReturn(paciente);

        Paciente resultado = useCase.execute(dados);

        assertNotNull(resultado);
        assertEquals(paciente, resultado);
        verify(pacienteGateway, times(1)).save(any(Paciente.class));
    }

    @Test
    @DisplayName("Deve criar paciente mesmo com campos opcionais vazios")
    void shouldCreatePacienteWithOptionalFieldsEmpty() {
        IPacienteRegistrationData dados = mock(IPacienteRegistrationData.class);
        Paciente paciente = mock(Paciente.class);

        var now = OffsetDateTime.now();

        when(dados.cpf()).thenReturn("12345678900");
        when(dados.nome()).thenReturn("Maria Souza");
        when(dados.primeiroDiaCadastro()).thenReturn(now);
        when(dados.cep()).thenReturn("");
        when(dados.numeroCasa()).thenReturn(0);
        when(pacienteGateway.save(any(Paciente.class))).thenReturn(paciente);

        Paciente resultado = useCase.execute(dados);

        assertNotNull(resultado);
        assertEquals(paciente, resultado);
        verify(pacienteGateway, times(1)).save(any(Paciente.class));
    }
}