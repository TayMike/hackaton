package com.fiap.hackaton.usecase.descarteEquipamento;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.descarteEquipamento.gateway.DescarteEquipamentoGateway;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.descarteEquipamento.dto.IDescarteEquipamentoRegistrationData;
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

class CreateDescarteEquipamentoUseCaseTest {

    private DescarteEquipamentoGateway descarteEquipamentoGateway;
    private EquipamentoGateway equipamentoGateway;
    private ColaboradorGateway colaboradorGateway;
    private HospitalGateway hospitalGateway;
    private CreateDescarteEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        descarteEquipamentoGateway = mock(DescarteEquipamentoGateway.class);
        equipamentoGateway = mock(EquipamentoGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateDescarteEquipamentoUseCase(descarteEquipamentoGateway, equipamentoGateway, colaboradorGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar descarte de equipamento com dados válidos")
    void shouldCreateDescarteEquipamentoWithValidData() throws HospitalNotFoundException, ColaboradorNotFoundException {
        UUID equipamentoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IDescarteEquipamentoRegistrationData dados = mock(IDescarteEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(List.of(equipamentoId));
        when(dados.quantidade()).thenReturn(List.of(1L));
        when(dados.colaboradorResponsavel()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.dataHoraDescarte()).thenReturn(OffsetDateTime.now());

        EquipamentoSchema equipamentoSchema = mock(EquipamentoSchema.class);
        when(equipamentoSchema.getId()).thenReturn(equipamentoId);
        when(equipamentoGateway.findAll()).thenReturn(List.of(equipamentoSchema));

        Equipamento equipamento = mock(Equipamento.class);
        when(equipamentoSchema.toEquipamento()).thenReturn(equipamento);

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        DescarteEquipamento descarteEquipamento = mock(DescarteEquipamento.class);
        when(descarteEquipamentoGateway.save(any(), any(), any(), any())).thenReturn(descarteEquipamento);

        DescarteEquipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findAll();
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(descarteEquipamentoGateway, times(1)).save(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar ColaboradorNotFoundException quando colaborador não existe")
    void shouldThrowColaboradorNotFoundExceptionWhenColaboradorDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IDescarteEquipamentoRegistrationData dados = mock(IDescarteEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(Collections.emptyList());
        when(dados.quantidade()).thenReturn(Collections.emptyList());
        when(dados.colaboradorResponsavel()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, never()).findById(hospitalId);
        verify(descarteEquipamentoGateway, never()).save(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IDescarteEquipamentoRegistrationData dados = mock(IDescarteEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(Collections.emptyList());
        when(dados.quantidade()).thenReturn(Collections.emptyList());
        when(dados.colaboradorResponsavel()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(descarteEquipamentoGateway, never()).save(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve criar descarte de equipamento ignorando equipamentos inexistentes")
    void shouldCreateDescarteEquipamentoIgnoringNonexistentEquipamentos() throws HospitalNotFoundException, ColaboradorNotFoundException {
        UUID equipamentoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IDescarteEquipamentoRegistrationData dados = mock(IDescarteEquipamentoRegistrationData.class);
        when(dados.equipamentos()).thenReturn(List.of(equipamentoId));
        when(dados.quantidade()).thenReturn(List.of(1L));
        when(dados.colaboradorResponsavel()).thenReturn(colaboradorId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.dataHoraDescarte()).thenReturn(OffsetDateTime.now());

        when(equipamentoGateway.findAll()).thenReturn(Collections.emptyList());

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaboradorSchema));

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        DescarteEquipamento descarteEquipamento = mock(DescarteEquipamento.class);
        when(descarteEquipamentoGateway.save(any(), any(), any(), any())).thenReturn(descarteEquipamento);

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador);

        DescarteEquipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findAll();
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(descarteEquipamentoGateway, times(1)).save(any(), any(), any(), any());
    }
}