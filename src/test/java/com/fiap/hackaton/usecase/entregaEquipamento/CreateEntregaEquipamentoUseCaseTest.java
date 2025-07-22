package com.fiap.hackaton.usecase.entregaEquipamento;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.entregaEquipamento.dto.IEntregaEquipamentoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateEntregaEquipamentoUseCaseTest {

    private EntregaEquipamentoGateway entregaEquipamentoGateway;
    private EquipamentoGateway equipamentoGateway;
    private ColaboradorGateway colaboradorGateway;
    private HospitalGateway hospitalGateway;
    private CreateEntregaEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        entregaEquipamentoGateway = mock(EntregaEquipamentoGateway.class);
        equipamentoGateway = mock(EquipamentoGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateEntregaEquipamentoUseCase(entregaEquipamentoGateway, equipamentoGateway, colaboradorGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar entrega de equipamento com dados válidos")
    void shouldCreateEntregaEquipamentoWithValidData() throws HospitalNotFoundException, ColaboradorNotFoundException {
        UUID equipamentoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaEquipamentoRegistrationData dados = mock(IEntregaEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(List.of(equipamentoId));
        when(dados.quantidade()).thenReturn(List.of(2L));
        when(dados.colaboradorRecebedor()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.dataHoraRecebimento()).thenReturn(LocalDateTime.now());

        EquipamentoSchema equipamentoSchema = mock(EquipamentoSchema.class);
        when(equipamentoSchema.getId()).thenReturn(equipamentoId);
        when(equipamentoGateway.findAll()).thenReturn(List.of(equipamentoSchema));

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));

        EntregaEquipamento entregaEquipamento = mock(EntregaEquipamento.class);
        when(entregaEquipamentoGateway.save(any(), any(), any(), any())).thenReturn(entregaEquipamento);

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador);

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        EntregaEquipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findAll();
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(entregaEquipamentoGateway, times(1)).save(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar ColaboradorNotFoundException quando colaborador não existe")
    void shouldThrowColaboradorNotFoundExceptionWhenColaboradorDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaEquipamentoRegistrationData dados = mock(IEntregaEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(Collections.emptyList());
        when(dados.quantidade()).thenReturn(Collections.emptyList());
        when(dados.colaboradorRecebedor()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, never()).findById(hospitalId);
        verify(entregaEquipamentoGateway, never()).save(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaEquipamentoRegistrationData dados = mock(IEntregaEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(Collections.emptyList());
        when(dados.quantidade()).thenReturn(Collections.emptyList());
        when(dados.colaboradorRecebedor()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(entregaEquipamentoGateway, never()).save(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve criar entrega ignorando equipamentos inexistentes")
    void shouldCreateEntregaEquipamentoIgnoringNonexistentEquipamentos() throws HospitalNotFoundException, ColaboradorNotFoundException {
        UUID equipamentoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaEquipamentoRegistrationData dados = mock(IEntregaEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(List.of(equipamentoId));
        when(dados.quantidade()).thenReturn(List.of(1L));
        when(dados.colaboradorRecebedor()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.dataHoraRecebimento()).thenReturn(LocalDateTime.now());

        when(equipamentoGateway.findAll()).thenReturn(Collections.emptyList());

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));

        EntregaEquipamento entregaEquipamento = mock(EntregaEquipamento.class);
        when(entregaEquipamentoGateway.save(any(), any(), any(), any())).thenReturn(entregaEquipamento);

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador);

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        EntregaEquipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findAll();
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(entregaEquipamentoGateway, times(1)).save(any(), any(), any(), any());
    }
}