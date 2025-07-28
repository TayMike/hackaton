package com.fiap.hackaton.usecase.core.paciente;

import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.usecase.core.paciente.dto.IPacienteRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

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

        var now = OffsetDateTime.now();

        when(dados.cpf()).thenReturn("12345678900");
        when(dados.nome()).thenReturn("João Silva");
        when(dados.primeiroDiaCadastro()).thenReturn(now);
        when(dados.cep()).thenReturn("01234567");
        when(dados.numeroCasa()).thenReturn(100);

        ArgumentCaptor<Paciente> captor = ArgumentCaptor.forClass(Paciente.class);
        when(pacienteGateway.save(any(Paciente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Paciente resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(pacienteGateway).save(captor.capture());

        Paciente capturado = captor.getValue();
        assertEquals("12345678900", capturado.getCpf());
        assertEquals("João Silva", capturado.getNome());
        assertEquals(now, capturado.getPrimeiroDiaCadastro());
        assertEquals("01234567", capturado.getCep());
        assertEquals(100, capturado.getNumeroCasa());
    }

    @Test
    @DisplayName("Deve criar paciente mesmo com campos opcionais vazios")
    void shouldCreatePacienteWithOptionalFieldsEmpty() {
        IPacienteRegistrationData dados = mock(IPacienteRegistrationData.class);

        var now = OffsetDateTime.now();

        when(dados.cpf()).thenReturn("12345678900");
        when(dados.nome()).thenReturn("Maria Souza");
        when(dados.primeiroDiaCadastro()).thenReturn(now);
        when(dados.cep()).thenReturn("");
        when(dados.numeroCasa()).thenReturn(0);

        ArgumentCaptor<Paciente> captor = ArgumentCaptor.forClass(Paciente.class);
        when(pacienteGateway.save(any(Paciente.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Paciente resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(pacienteGateway).save(captor.capture());

        Paciente capturado = captor.getValue();
        assertEquals("12345678900", capturado.getCpf());
        assertEquals("Maria Souza", capturado.getNome());
        assertEquals(now, capturado.getPrimeiroDiaCadastro());
        assertEquals("", capturado.getCep());
        assertEquals(0, capturado.getNumeroCasa());
    }
}