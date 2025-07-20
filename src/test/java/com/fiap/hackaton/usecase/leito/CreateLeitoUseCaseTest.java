package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.leito.dto.ILeitoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateLeitoUseCaseTest {

    private LeitoGateway leitoGateway;
    private CreateLeitoUseCase useCase;

    Hospital hospitalX = mock(Hospital.class);
    Paciente pacienteY = mock(Paciente.class);

    @BeforeEach
    void setUp() {
        leitoGateway = mock(LeitoGateway.class);
        useCase = new CreateLeitoUseCase(leitoGateway);
    }

    @Test
    @DisplayName("Deve criar e salvar um leito com dados válidos")
    void shouldCreateAndSaveLeitoWithValidData() {
        ILeitoRegistrationData dados = mock(ILeitoRegistrationData.class);
        when(dados.identificacao()).thenReturn("L1");
        when(dados.pavilhao()).thenReturn("A");
        when(dados.quarto()).thenReturn("101");

        when(dados.hospital()).thenReturn(hospitalX);
        when(dados.paciente()).thenReturn(pacienteY);

        Leito leitoCriado = new Leito("L1", "A", "101", hospitalX, pacienteY);
        when(leitoGateway.save(any(Leito.class))).thenReturn(leitoCriado);

        Leito resultado = useCase.execute(dados);

        assertNotNull(resultado);
        assertEquals("L1", resultado.getIdentificacao());
        assertEquals("A", resultado.getPavilhao());
        assertEquals("101", resultado.getQuarto());
        assertEquals(hospitalX, resultado.getHospital());
        assertEquals(pacienteY, resultado.getPaciente());
        verify(leitoGateway, times(1)).save(any(Leito.class));
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se dados forem nulos")
    void shouldThrowExceptionWhenDadosIsNull() {
        assertThrows(NullPointerException.class, () -> useCase.execute(null));
    }

    @Test
    @DisplayName("Deve lançar exceção se o gateway retornar erro ao salvar")
    void shouldThrowExceptionWhenGatewayFails() {
        ILeitoRegistrationData dados = mock(ILeitoRegistrationData.class);
        when(dados.identificacao()).thenReturn("L2");
        when(dados.pavilhao()).thenReturn("B");
        when(dados.quarto()).thenReturn("102");
        when(dados.hospital()).thenReturn(hospitalX);
        when(dados.paciente()).thenReturn(pacienteY);

        when(leitoGateway.save(any(Leito.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        assertThrows(RuntimeException.class, () -> useCase.execute(dados));
    }
}

