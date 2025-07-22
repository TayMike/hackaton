package com.fiap.hackaton.usecase.coletaEquipamento;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.coletaEquipamento.gateway.ColetaEquipamentoGateway;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.ColetaEquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.coletaEquipamento.dto.IColetaEquipamentoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateColetaEquipamentoUseCaseTest {

    private ColetaEquipamentoGateway coletaEquipamentoGateway;
    private EquipamentoGateway equipamentoGateway;
    private ColaboradorGateway colaboradorGateway;
    private HospitalGateway hospitalGateway;
    private CreateColetaEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        coletaEquipamentoGateway = mock(ColetaEquipamentoGateway.class);
        equipamentoGateway = mock(EquipamentoGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateColetaEquipamentoUseCase(coletaEquipamentoGateway, equipamentoGateway, colaboradorGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar coleta de equipamento com dados válidos")
    void shouldCreateColetaEquipamentoWithValidData() throws EquipamentoNotFoundException {
        UUID entregadorId = UUID.randomUUID();
        UUID responsavelId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();
        UUID equipamentoId = UUID.randomUUID();

        IColetaEquipamentoRegistrationData dados = mock(IColetaEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(List.of(equipamentoId));
        when(dados.quantidades()).thenReturn(List.of(2L));
        when(dados.colaboradorEntregador()).thenReturn(entregadorId);
        when(dados.colaboradorResponsavel()).thenReturn(responsavelId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.dataHoraColeta()).thenReturn(OffsetDateTime.now());

        EquipamentoSchema equipamentoSchema = mock(EquipamentoSchema.class);
        Equipamento equipamento = mock(Equipamento.class);
        when(equipamentoGateway.findAll()).thenReturn(List.of(equipamentoSchema));
        when(equipamentoSchema.getId()).thenReturn(equipamentoId);
        when(equipamentoSchema.toEquipamento()).thenReturn(equipamento);

        ColaboradorSchema entregadorSchema = mock(ColaboradorSchema.class);
        ColaboradorSchema responsavelSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(entregadorId)).thenReturn(Optional.of(entregadorSchema));
        when(colaboradorGateway.findById(responsavelId)).thenReturn(Optional.of(responsavelSchema));

        Colaborador entregador = mock(Colaborador.class);
        Colaborador responsavel= mock(Colaborador.class);
        when(entregadorSchema.toColaborador()).thenReturn(entregador);
        when(responsavelSchema.toColaborador()).thenReturn(responsavel);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));

        ColetaEquipamento coletaEquipamento = mock(ColetaEquipamento.class);
        ColetaEquipamentoSchema coletaEquipamentoSchema = mock(ColetaEquipamentoSchema.class);
        when(coletaEquipamentoGateway.save(any(), any(), any(), any(), any())).thenReturn(coletaEquipamentoSchema);
        when(coletaEquipamentoSchema.toColetaEquipamento()).thenReturn(coletaEquipamento);

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        ColetaEquipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findAll();
        verify(colaboradorGateway, times(1)).findById(entregadorId);
        verify(colaboradorGateway, times(1)).findById(responsavelId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(coletaEquipamentoGateway, times(1)).save(any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar EquipamentoNotFoundException quando entregador não existe")
    void shouldThrowEquipamentoNotFoundExceptionWhenEntregadorDoesNotExist() {
        UUID entregadorId = UUID.randomUUID();
        UUID responsavelId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IColetaEquipamentoRegistrationData dados = mock(IColetaEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(Collections.emptyList());
        when(dados.quantidades()).thenReturn(Collections.emptyList());
        when(dados.colaboradorEntregador()).thenReturn(entregadorId);
        when(dados.colaboradorResponsavel()).thenReturn(responsavelId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(colaboradorGateway.findById(entregadorId)).thenReturn(Optional.empty());

        assertThrows(EquipamentoNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(entregadorId);
        verify(colaboradorGateway, never()).findById(responsavelId);
        verify(hospitalGateway, never()).findById(hospitalId);
        verify(coletaEquipamentoGateway, never()).save(any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar EquipamentoNotFoundException quando responsável não existe")
    void shouldThrowEquipamentoNotFoundExceptionWhenResponsavelDoesNotExist() {
        UUID entregadorId = UUID.randomUUID();
        UUID responsavelId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IColetaEquipamentoRegistrationData dados = mock(IColetaEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(Collections.emptyList());
        when(dados.quantidades()).thenReturn(Collections.emptyList());
        when(dados.colaboradorEntregador()).thenReturn(entregadorId);
        when(dados.colaboradorResponsavel()).thenReturn(responsavelId);
        when(dados.hospital()).thenReturn(hospitalId);

        ColaboradorSchema entregadorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(entregadorId)).thenReturn(Optional.of(entregadorSchema));
        when(colaboradorGateway.findById(responsavelId)).thenReturn(Optional.empty());

        assertThrows(EquipamentoNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(entregadorId);
        verify(colaboradorGateway, times(1)).findById(responsavelId);
        verify(hospitalGateway, never()).findById(hospitalId);
        verify(coletaEquipamentoGateway, never()).save(any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar EquipamentoNotFoundException quando hospital não existe")
    void shouldThrowEquipamentoNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID entregadorId = UUID.randomUUID();
        UUID responsavelId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IColetaEquipamentoRegistrationData dados = mock(IColetaEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(Collections.emptyList());
        when(dados.quantidades()).thenReturn(Collections.emptyList());
        when(dados.colaboradorEntregador()).thenReturn(entregadorId);
        when(dados.colaboradorResponsavel()).thenReturn(responsavelId);
        when(dados.hospital()).thenReturn(hospitalId);

        ColaboradorSchema entregadorSchema = mock(ColaboradorSchema.class);
        ColaboradorSchema responsavelSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(entregadorId)).thenReturn(Optional.of(entregadorSchema));
        when(colaboradorGateway.findById(responsavelId)).thenReturn(Optional.of(responsavelSchema));

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(EquipamentoNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(entregadorId);
        verify(colaboradorGateway, times(1)).findById(responsavelId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(coletaEquipamentoGateway, never()).save(any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve criar coleta de equipamento ignorando equipamentos inexistentes")
    void shouldCreateColetaEquipamentoIgnoringNonexistentEquipamentos() throws EquipamentoNotFoundException {
        UUID entregadorId = UUID.randomUUID();
        UUID responsavelId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();
        UUID equipamentoId = UUID.randomUUID();

        IColetaEquipamentoRegistrationData dados = mock(IColetaEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(List.of(equipamentoId));
        when(dados.quantidades()).thenReturn(List.of(1L));
        when(dados.colaboradorEntregador()).thenReturn(entregadorId);
        when(dados.colaboradorResponsavel()).thenReturn(responsavelId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.dataHoraColeta()).thenReturn(OffsetDateTime.now());

        when(equipamentoGateway.findAll()).thenReturn(Collections.emptyList());

        ColaboradorSchema entregadorSchema = mock(ColaboradorSchema.class);
        ColaboradorSchema responsavelSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(entregadorId)).thenReturn(Optional.of(entregadorSchema));
        when(colaboradorGateway.findById(responsavelId)).thenReturn(Optional.of(responsavelSchema));

        Colaborador entregador = mock(Colaborador.class);
        Colaborador responsavel= mock(Colaborador.class);
        when(entregadorSchema.toColaborador()).thenReturn(entregador);
        when(responsavelSchema.toColaborador()).thenReturn(responsavel);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));

        ColetaEquipamento coletaEquipamento = mock(ColetaEquipamento.class);
        ColetaEquipamentoSchema coletaEquipamentoSchema = mock(ColetaEquipamentoSchema.class);
        when(coletaEquipamentoGateway.save(any(), any(), any(), any(), any())).thenReturn(coletaEquipamentoSchema);
        when(coletaEquipamentoSchema.toColetaEquipamento()).thenReturn(coletaEquipamento);

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        ColetaEquipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findAll();
        verify(colaboradorGateway, times(1)).findById(entregadorId);
        verify(colaboradorGateway, times(1)).findById(responsavelId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(coletaEquipamentoGateway, times(1)).save(any(), any(), any(), any(), any());
    }
}