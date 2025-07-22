package com.fiap.hackaton.usecase.equipamento;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.usecase.equipamento.dto.IEquipamentoUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateEquipamentoUseCaseTest {

    private EquipamentoGateway equipamentoGateway;
    private HospitalGateway hospitalGateway;
    private UpdateEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        equipamentoGateway = mock(EquipamentoGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new UpdateEquipamentoUseCase(equipamentoGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve atualizar equipamento com dados válidos")
    void shouldUpdateEquipamentoWithValidData() throws EquipamentoNotFoundException, ColaboradorNotFoundException, HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        IEquipamentoUpdateData dados = mock(IEquipamentoUpdateData.class);
        EquipamentoSchema equipamentoSchema = mock(EquipamentoSchema.class);
        Equipamento equipamento = mock(Equipamento.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);

        when(equipamentoGateway.findById(id)).thenReturn(Optional.of(equipamentoSchema));
        when(equipamentoSchema.toEquipamento()).thenReturn(equipamento);
        when(hospitalGateway.findById(id)).thenReturn(Optional.of(hospitalSchema));
        when(equipamentoGateway.update(equipamento, hospitalSchema)).thenReturn(equipamentoSchema);
        when(equipamentoSchema.toEquipamento()).thenReturn(equipamento);

        when(dados.nome()).thenReturn("Monitor");
        when(dados.custo()).thenReturn(BigDecimal.valueOf(2000));
        when(dados.tempoGarantia()).thenReturn(OffsetDateTime.of(0,6,1,0,0,0,0, ZoneOffset.UTC));
        when(dados.proximaManutencaoPreventiva()).thenReturn(OffsetDateTime.now().plusMonths(12));
        when(dados.marca()).thenReturn("GE");
        when(dados.hospital()).thenReturn(UUID.randomUUID());

        Equipamento resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findById(id);
        verify(hospitalGateway, times(1)).findById(id);
        verify(equipamentoGateway, times(1)).update(equipamento, hospitalSchema);
    }

    @Test
    @DisplayName("Deve lançar EquipamentoNotFoundException quando equipamento não existe")
    void shouldThrowEquipamentoNotFoundExceptionWhenEquipamentoDoesNotExist() {
        UUID id = UUID.randomUUID();
        IEquipamentoUpdateData dados = mock(IEquipamentoUpdateData.class);

        when(equipamentoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EquipamentoNotFoundException.class, () -> useCase.execute(id, dados));
        verify(equipamentoGateway, times(1)).findById(id);
        verify(hospitalGateway, never()).findById(any());
        verify(equipamentoGateway, never()).update(any(), any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID id = UUID.randomUUID();
        IEquipamentoUpdateData dados = mock(IEquipamentoUpdateData.class);
        EquipamentoSchema equipamentoSchema = mock(EquipamentoSchema.class);

        when(equipamentoGateway.findById(id)).thenReturn(Optional.of(equipamentoSchema));
        when(equipamentoSchema.toEquipamento()).thenReturn(mock(Equipamento.class));
        when(hospitalGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(id, dados));
        verify(equipamentoGateway, times(1)).findById(id);
        verify(hospitalGateway, times(1)).findById(id);
        verify(equipamentoGateway, never()).update(any(), any());
    }

    @Test
    @DisplayName("Deve ignorar campos nulos ou em branco e não atualizar esses valores")
    void shouldIgnoreNullOrBlankFieldsAndNotUpdateThem() throws EquipamentoNotFoundException, ColaboradorNotFoundException, HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        IEquipamentoUpdateData dados = mock(IEquipamentoUpdateData.class);
        EquipamentoSchema equipamentoSchema = mock(EquipamentoSchema.class);
        Equipamento equipamento = mock(Equipamento.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);

        when(equipamentoGateway.findById(id)).thenReturn(Optional.of(equipamentoSchema));
        when(equipamentoSchema.toEquipamento()).thenReturn(equipamento);
        when(hospitalGateway.findById(id)).thenReturn(Optional.of(hospitalSchema));
        when(equipamentoGateway.update(equipamento, hospitalSchema)).thenReturn(equipamentoSchema);
        when(equipamentoSchema.toEquipamento()).thenReturn(equipamento);

        when(dados.nome()).thenReturn("");
        when(dados.custo()).thenReturn(BigDecimal.ZERO);
        when(dados.tempoGarantia()).thenReturn(null);
        when(dados.proximaManutencaoPreventiva()).thenReturn(null);
        when(dados.marca()).thenReturn(null);
        when(dados.hospital()).thenReturn(null);

        Equipamento resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findById(id);
        verify(hospitalGateway, times(1)).findById(id);
        verify(equipamentoGateway, times(1)).update(equipamento, hospitalSchema);
    }
}