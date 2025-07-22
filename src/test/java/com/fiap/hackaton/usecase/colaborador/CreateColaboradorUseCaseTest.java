package com.fiap.hackaton.usecase.colaborador;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.colaborador.CreateColaboradorUseCase;
import com.fiap.hackaton.usecase.colaborador.dto.IColaboradorRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
        var hospital1Id = UUID.randomUUID();
        when(dados.hospital()).thenReturn(hospital1Id);
        when(dados.cpf()).thenReturn("12345678900");
        when(dados.nome()).thenReturn("João Silva");
        when(dados.matricula()).thenReturn("MAT123");
        when(dados.primeiroDiaCadastro()).thenReturn(OffsetDateTime.of(2024,06,01,0,0,0,0, ZoneOffset.UTC));
        when(dados.cep()).thenReturn("01234567");
        when(dados.numeroCasa()).thenReturn(100);
        when(dados.setor()).thenReturn("Emergência");
        when(dados.ativo()).thenReturn(true);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospital1Id)).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(mock(com.fiap.hackaton.entity.hospital.model.Hospital.class));

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.save(any(Colaborador.class), eq(hospitalSchema))).thenReturn(colaboradorSchema);

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador);

        Colaborador resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).findById(hospital1Id);
        verify(colaboradorGateway, times(1)).save(any(Colaborador.class), eq(hospitalSchema));
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        IColaboradorRegistrationData dados = mock(IColaboradorRegistrationData.class);
        var hospital2Id = UUID.randomUUID();
        when(dados.hospital()).thenReturn(hospital2Id);

        when(hospitalGateway.findById(hospital2Id)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(hospitalGateway, times(1)).findById(hospital2Id);
        verifyNoInteractions(colaboradorGateway);
    }

    @Test
    @DisplayName("Deve lançar NullPointerException se dados forem nulos")
    void shouldThrowExceptionWhenDadosIsNull() {
        assertThrows(NullPointerException.class, () -> useCase.execute(null));
    }

    @Test
    @DisplayName("Deve lançar exceção se o gateway de colaborador falhar ao salvar")
    void shouldThrowExceptionWhenColaboradorGatewayFails() {
        IColaboradorRegistrationData dados = mock(IColaboradorRegistrationData.class);
        var hospital3Id = UUID.randomUUID();
        when(dados.hospital()).thenReturn(hospital3Id);
        when(dados.cpf()).thenReturn("98765432100");
        when(dados.nome()).thenReturn("Maria Souza");
        when(dados.matricula()).thenReturn("MAT456");
        when(dados.primeiroDiaCadastro()).thenReturn(OffsetDateTime.of(2024,06,01,0,0,0,0, ZoneOffset.UTC));
        when(dados.cep()).thenReturn("76543210");
        when(dados.numeroCasa()).thenReturn(200);
        when(dados.setor()).thenReturn("UTI");
        when(dados.ativo()).thenReturn(false);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospital3Id)).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(mock(com.fiap.hackaton.entity.hospital.model.Hospital.class));

        when(colaboradorGateway.save(any(Colaborador.class), eq(hospitalSchema)))
                .thenThrow(new RuntimeException("Erro ao salvar colaborador"));

        assertThrows(RuntimeException.class, () -> useCase.execute(dados));
    }
}