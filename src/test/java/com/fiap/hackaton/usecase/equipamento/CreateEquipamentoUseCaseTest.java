package com.fiap.hackaton.usecase.equipamento;

import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.equipamento.dto.IEquipamentoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateEquipamentoUseCaseTest {

    private EquipamentoGateway equipamentoGateway;
    private HospitalGateway hospitalGateway;
    private CreateEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        equipamentoGateway = mock(EquipamentoGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateEquipamentoUseCase(equipamentoGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar equipamento com dados válidos")
    void shouldCreateEquipamentoWithValidData() throws HospitalNotFoundException {
        UUID hospitalId = UUID.randomUUID();
        IEquipamentoRegistrationData dados = mock(IEquipamentoRegistrationData.class);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.nome()).thenReturn("Monitor");
        when(dados.custo()).thenReturn(BigDecimal.valueOf(1000));
        when(dados.tempoGarantia()).thenReturn(OffsetDateTime.of(0,6,1,0,0,0,0, ZoneOffset.UTC));
        when(dados.proximaManutencaoPreventiva()).thenReturn(OffsetDateTime.now().plusMonths(6));
        when(dados.marca()).thenReturn("Philips");

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        EquipamentoSchema equipamentoSchema = mock(EquipamentoSchema.class);
        when(equipamentoSchema.toEquipamento()).thenReturn(mock(Equipamento.class));
        when(equipamentoGateway.save(any(), eq(hospitalSchema))).thenReturn(equipamentoSchema);

        Equipamento equipamento = mock(Equipamento.class);
        when(equipamentoSchema.toEquipamento()).thenReturn(equipamento);

        Equipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(equipamentoGateway, times(1)).save(any(), eq(hospitalSchema));
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID hospitalId = UUID.randomUUID();
        IEquipamentoRegistrationData dados = mock(IEquipamentoRegistrationData.class);
        when(dados.hospital()).thenReturn(hospitalId);

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(equipamentoGateway, never()).save(any(), any());
    }
}