package com.fiap.hackaton.usecase.entregaInsumo;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;
import com.fiap.hackaton.usecase.entregaInsumo.dto.IEntregaInsumoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateEntregaInsumoUseCaseTest {

    private EntregaInsumoGateway entregaInsumoGateway;
    private InsumoGateway insumoGateway;
    private ColaboradorGateway colaboradorGateway;
    private HospitalGateway hospitalGateway;
    private CreateEntregaInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        entregaInsumoGateway = mock(EntregaInsumoGateway.class);
        insumoGateway = mock(InsumoGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateEntregaInsumoUseCase(entregaInsumoGateway, insumoGateway, colaboradorGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar entrega de insumo com dados válidos")
    void shouldCreateEntregaInsumoWithValidData() throws HospitalNotFoundException, ColaboradorNotFoundException {
        UUID insumoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaInsumoRegistrationData dados = mock(IEntregaInsumoRegistrationData.class);
        when(dados.insumo()).thenReturn(List.of(insumoId));
        when(dados.quantidade()).thenReturn(List.of(5L));
        when(dados.colaboradorRecebedor()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.dataHoraRecebimento()).thenReturn(OffsetDateTime.now());

        InsumoSchema insumoSchema = mock(InsumoSchema.class);
        when(insumoSchema.getId()).thenReturn(insumoId);
        when(insumoGateway.findAll()).thenReturn(List.of(insumoSchema));

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));

        EntregaInsumo entregaInsumo = mock(EntregaInsumo.class);
        when(entregaInsumoGateway.save(any(), any(), any(), any())).thenReturn(entregaInsumo);

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador);

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        EntregaInsumo resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(insumoGateway, times(1)).findAll();
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(entregaInsumoGateway, times(1)).save(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar ColaboradorNotFoundException quando colaborador não existe")
    void shouldThrowColaboradorNotFoundExceptionWhenColaboradorDoesNotExist() {
        UUID insumoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaInsumoRegistrationData dados = mock(IEntregaInsumoRegistrationData.class);
        when(dados.insumo()).thenReturn(List.of(insumoId));
        when(dados.quantidade()).thenReturn(List.of(1L));
        when(dados.colaboradorRecebedor()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);

        when(insumoGateway.findAll()).thenReturn(Collections.emptyList());
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, never()).findById(hospitalId);
        verify(entregaInsumoGateway, never()).save(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID insumoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaInsumoRegistrationData dados = mock(IEntregaInsumoRegistrationData.class);
        when(dados.insumo()).thenReturn(List.of(insumoId));
        when(dados.quantidade()).thenReturn(List.of(1L));
        when(dados.colaboradorRecebedor()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(entregaInsumoGateway, never()).save(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve criar entrega ignorando insumos inexistentes")
    void shouldCreateEntregaInsumoIgnoringNonexistentInsumos() throws HospitalNotFoundException, ColaboradorNotFoundException {
        UUID insumoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaInsumoRegistrationData dados = mock(IEntregaInsumoRegistrationData.class);
        when(dados.insumo()).thenReturn(List.of(insumoId));
        when(dados.quantidade()).thenReturn(List.of(1L));
        when(dados.colaboradorRecebedor()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.dataHoraRecebimento()).thenReturn(OffsetDateTime.now());

        when(insumoGateway.findAll()).thenReturn(Collections.emptyList());

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));

        EntregaInsumo entregaInsumo = mock(EntregaInsumo.class);
        when(entregaInsumoGateway.save(any(), any(), any(), any())).thenReturn(entregaInsumo);

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador);

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        EntregaInsumo resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(insumoGateway, times(1)).findAll();
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(entregaInsumoGateway, times(1)).save(any(), any(), any(), any());
    }
}