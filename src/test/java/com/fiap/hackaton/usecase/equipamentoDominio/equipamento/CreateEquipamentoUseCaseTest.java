package com.fiap.hackaton.usecase.equipamentoDominio.equipamento;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.dto.IEquipamentoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateEquipamentoUseCaseTest {

    private EquipamentoGateway equipamentoGateway;
    private CreateEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        equipamentoGateway = mock(EquipamentoGateway.class);
        useCase = new CreateEquipamentoUseCase(equipamentoGateway);
    }

    @Test
    @DisplayName("Deve criar equipamento com dados válidos")
    void shouldCreateEquipamentoWithValidData() throws HospitalNotFoundException {
        IEquipamentoRegistrationData dados = mock(IEquipamentoRegistrationData.class);

        when(dados.nome()).thenReturn("Monitor");
        when(dados.custo()).thenReturn(BigDecimal.valueOf(1500));
        when(dados.tempoGarantia()).thenReturn(OffsetDateTime.now().plusYears(1));
        when(dados.proximaManutencaoPreventiva()).thenReturn(OffsetDateTime.now().plusMonths(6));
        when(dados.numeroSerie()).thenReturn("SN123456");
        when(dados.marca()).thenReturn("Philips");
        when(dados.descarte()).thenReturn(null);

        Equipamento equipamentoMock = mock(Equipamento.class);
        when(equipamentoGateway.save(any())).thenReturn(equipamentoMock);

        Equipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).save(any(Equipamento.class));
    }
}