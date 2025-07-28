package com.fiap.hackaton.usecase.core.colaborador;

import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.colaborador.dto.IColaboradorRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateColaboradorUseCaseTest {

    private ColaboradorGateway colaboradorGateway;
    private HospitalGateway hospitalGateway;
    private CreateColaboradorUseCase useCase;

    @BeforeEach
    void setUp() {
        colaboradorGateway = mock(ColaboradorGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateColaboradorUseCase(colaboradorGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar colaborador quando hospital existe")
    void shouldCreateColaboradorWhenHospitalExists() throws HospitalNotFoundException {
        IColaboradorRegistrationData dados = mock(IColaboradorRegistrationData.class);
        UUID hospitalId = UUID.randomUUID();
        Hospital hospital = mock(Hospital.class);
        Colaborador colaboradorEsperado = mock(Colaborador.class);

        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.cpf()).thenReturn("12345678900");
        when(dados.nome()).thenReturn("João Silva");
        when(dados.matricula()).thenReturn("MAT123");
        when(dados.cep()).thenReturn("01234567");
        when(dados.numeroCasa()).thenReturn(100);
        when(dados.setor()).thenReturn("Emergência");
        when(dados.primeiroDiaCadastro()).thenReturn(OffsetDateTime.of(2024, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        when(dados.ultimoDiaCadastro()).thenReturn(OffsetDateTime.of(2025, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC));

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));
        when(hospital.getId()).thenReturn(hospitalId);

        when(colaboradorGateway.save(any(Colaborador.class))).thenReturn(colaboradorEsperado);

        Colaborador resultado = useCase.execute(dados);

        assertNotNull(resultado);
        assertEquals(colaboradorEsperado, resultado);

        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(colaboradorGateway, times(1)).save(any(Colaborador.class));
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        IColaboradorRegistrationData dados = mock(IColaboradorRegistrationData.class);
        UUID hospitalId = UUID.randomUUID();

        when(dados.hospitalId()).thenReturn(hospitalId);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));

        verify(hospitalGateway, times(1)).findById(hospitalId);
        verifyNoInteractions(colaboradorGateway);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se dados forem nulos")
    void shouldThrowNullPointerExceptionWhenDadosIsNull() {
        assertThrows(NullPointerException.class, () -> useCase.execute(null));
    }

    @Test
    @DisplayName("Deve lançar exceção se o gateway de colaborador falhar ao salvar")
    void shouldThrowExceptionWhenColaboradorGatewayFails() {
        IColaboradorRegistrationData dados = mock(IColaboradorRegistrationData.class);
        UUID hospitalId = UUID.randomUUID();
        Hospital hospital = mock(Hospital.class);

        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.cpf()).thenReturn("98765432100");
        when(dados.nome()).thenReturn("Maria Souza");
        when(dados.matricula()).thenReturn("MAT456");
        when(dados.cep()).thenReturn("76543210");
        when(dados.numeroCasa()).thenReturn(200);
        when(dados.setor()).thenReturn("UTI");
        when(dados.primeiroDiaCadastro()).thenReturn(OffsetDateTime.of(2024, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        when(dados.ultimoDiaCadastro()).thenReturn(OffsetDateTime.of(2025, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC));

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));
        when(hospital.getId()).thenReturn(hospitalId);

        when(colaboradorGateway.save(any(Colaborador.class))).thenThrow(new RuntimeException("Erro ao salvar"));

        assertThrows(RuntimeException.class, () -> useCase.execute(dados));
    }
}