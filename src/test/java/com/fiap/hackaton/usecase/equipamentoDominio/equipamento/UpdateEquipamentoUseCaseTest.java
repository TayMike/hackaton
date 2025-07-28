package com.fiap.hackaton.usecase.equipamentoDominio.equipamento;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.dto.IEquipamentoUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateEquipamentoUseCaseTest {

    private EquipamentoGateway equipamentoGateway;
    private UpdateEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        equipamentoGateway = mock(EquipamentoGateway.class);
        useCase = new UpdateEquipamentoUseCase(equipamentoGateway);
    }

    @Test
    @DisplayName("Deve atualizar equipamento com dados válidos")
    void shouldUpdateEquipamentoWithValidData() throws EquipamentoNotFoundException {
        UUID id = UUID.randomUUID();
        IEquipamentoUpdateData dados = mock(IEquipamentoUpdateData.class);
        Equipamento equipamento = mock(Equipamento.class);

        when(equipamentoGateway.findById(id)).thenReturn(Optional.of(equipamento));
        when(equipamentoGateway.update(equipamento)).thenReturn(equipamento);

        when(dados.nome()).thenReturn("Monitor");
        when(dados.custo()).thenReturn(BigDecimal.valueOf(2000));
        when(dados.tempoGarantia()).thenReturn(OffsetDateTime.of(0, 6, 1, 0, 0, 0, 0, ZoneOffset.UTC));
        when(dados.proximaManutencaoPreventiva()).thenReturn(OffsetDateTime.now().plusMonths(12));
        when(dados.marca()).thenReturn("GE");
        when(dados.descarte()).thenReturn(null);

        Equipamento resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findById(id);
        verify(equipamentoGateway, times(1)).update(equipamento);

        verify(equipamento).setNome("Monitor");
        verify(equipamento).setCusto(BigDecimal.valueOf(2000));
        verify(equipamento).setTempoGarantia(any(OffsetDateTime.class));
        verify(equipamento).setProximaManutencaoPreventiva(any(OffsetDateTime.class));
        verify(equipamento).setMarca("GE");
        verify(equipamento, never()).setDescarte(any());
    }

    @Test
    @DisplayName("Deve lançar EquipamentoNotFoundException quando equipamento não existe")
    void shouldThrowEquipamentoNotFoundExceptionWhenEquipamentoDoesNotExist() {
        UUID id = UUID.randomUUID();
        IEquipamentoUpdateData dados = mock(IEquipamentoUpdateData.class);

        when(equipamentoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EquipamentoNotFoundException.class, () -> useCase.execute(id, dados));
        verify(equipamentoGateway, times(1)).findById(id);
        verify(equipamentoGateway, never()).update(any());
    }

    @Test
    @DisplayName("Deve ignorar campos nulos, vazios ou zero e não chamar setters para eles")
    void shouldIgnoreNullBlankOrZeroFields() throws EquipamentoNotFoundException {
        UUID id = UUID.randomUUID();
        IEquipamentoUpdateData dados = mock(IEquipamentoUpdateData.class);
        Equipamento equipamento = mock(Equipamento.class);

        when(equipamentoGateway.findById(id)).thenReturn(Optional.of(equipamento));
        when(equipamentoGateway.update(equipamento)).thenReturn(equipamento);

        when(dados.nome()).thenReturn("");
        when(dados.custo()).thenReturn(BigDecimal.ZERO);
        when(dados.tempoGarantia()).thenReturn(null);
        when(dados.proximaManutencaoPreventiva()).thenReturn(null);
        when(dados.marca()).thenReturn("  ");  // blank string
        when(dados.descarte()).thenReturn(null);

        Equipamento resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findById(id);
        verify(equipamentoGateway, times(1)).update(equipamento);

        verify(equipamento, never()).setNome(any());
        verify(equipamento, never()).setCusto(any());
        verify(equipamento, never()).setTempoGarantia(any());
        verify(equipamento, never()).setProximaManutencaoPreventiva(any());
        verify(equipamento, never()).setMarca(any());
        verify(equipamento, never()).setDescarte(any());
    }
}