package com.fiap.hackaton.usecase.colaborador;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.colaborador.dto.IColaboradorUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateColaboradorUseCaseTest {

    private ColaboradorGateway colaboradorGateway;
    private HospitalGateway hospitalGateway;
    private UpdateColaboradorUseCase useCase;

    @BeforeEach
    void setUp() {
        colaboradorGateway = mock(ColaboradorGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new UpdateColaboradorUseCase(colaboradorGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve atualizar colaborador com dados válidos")
    void shouldUpdateColaboradorWithValidData() throws HospitalNotFoundException, ColaboradorNotFoundException {
        var hospital1Id = UUID.randomUUID();

        IColaboradorUpdateData dados = mock(IColaboradorUpdateData.class);
        when(dados.hospital()).thenReturn(hospital1Id);
        when(dados.nome()).thenReturn("Novo Nome");
        when(dados.cep()).thenReturn("12345678");
        when(dados.numeroCasa()).thenReturn(200);
        when(dados.setor()).thenReturn("Novo Setor");
        when(dados.ativo()).thenReturn(true);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospital1Id)).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(mock(com.fiap.hackaton.entity.hospital.model.Hospital.class));

        UUID colaboradorId = UUID.randomUUID();

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));

        Colaborador colaborador1 = mock(Colaborador.class);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador1);

        when(colaboradorGateway.update(colaborador1, hospitalSchema)).thenReturn(colaboradorSchema);

        Colaborador resultado = useCase.execute(colaboradorId, dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).findById(hospital1Id);
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(colaboradorGateway, times(1)).update(colaborador1, hospitalSchema);
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        var hospital2Id = UUID.randomUUID();
        IColaboradorUpdateData dados = mock(IColaboradorUpdateData.class);
        when(dados.hospital()).thenReturn(hospital2Id);

        when(hospitalGateway.findById(hospital2Id)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(colaboradorId, dados));
        verify(hospitalGateway, times(1)).findById(hospital2Id);
        verifyNoInteractions(colaboradorGateway);
    }

    @Test
    @DisplayName("Deve lançar ColaboradorNotFoundException quando colaborador não existe")
    void shouldThrowColaboradorNotFoundExceptionWhenColaboradorDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        var hospital3Id = UUID.randomUUID();
        IColaboradorUpdateData dados = mock(IColaboradorUpdateData.class);
        when(dados.hospital()).thenReturn(hospital3Id);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospital3Id)).thenReturn(Optional.of(hospitalSchema));
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(colaboradorId, dados));
        verify(hospitalGateway, times(1)).findById(hospital3Id);
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
    }

    @Test
    @DisplayName("Deve atualizar apenas campos não nulos e não vazios")
    void shouldUpdateOnlyNonNullAndNonBlankFields() throws HospitalNotFoundException, ColaboradorNotFoundException {
        UUID colaboradorId = UUID.randomUUID();
        var hospital4Id = UUID.randomUUID();
        IColaboradorUpdateData dados = mock(IColaboradorUpdateData.class);
        when(dados.hospital()).thenReturn(hospital4Id);
        when(dados.nome()).thenReturn("");
        when(dados.cep()).thenReturn(null);
        when(dados.numeroCasa()).thenReturn(null);
        when(dados.setor()).thenReturn("  ");
        when(dados.ativo()).thenReturn(null);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospital4Id)).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(mock(com.fiap.hackaton.entity.hospital.model.Hospital.class));

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador);

        when(colaboradorGateway.update(colaborador, hospitalSchema)).thenReturn(colaboradorSchema);

        Colaborador resultado = useCase.execute(colaboradorId, dados);

        assertNotNull(resultado);
        verify(colaborador, never()).setNome(anyString());
        verify(colaborador, never()).setCep(anyString());
        verify(colaborador, never()).setNumeroCasa(any());
        verify(colaborador, never()).setSetor(anyString());
        verify(colaborador, never()).setAtivo(any());
        verify(colaborador, times(1)).setHospital(any());
    }
}