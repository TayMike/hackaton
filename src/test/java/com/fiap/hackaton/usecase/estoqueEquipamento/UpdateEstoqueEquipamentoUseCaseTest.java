package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueEquipamentoSchema;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateEstoqueEquipamentoUseCaseTest {

    private EstoqueEquipamentoGateway estoqueGateway;
    private EquipamentoGateway equipamentoGateway;
    private HospitalGateway hospitalGateway;
    private UpdateEstoqueEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueEquipamentoGateway.class);
        equipamentoGateway = mock(EquipamentoGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new UpdateEstoqueEquipamentoUseCase(estoqueGateway, equipamentoGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve atualizar estoque de equipamento com dados válidos")
    void shouldUpdateEstoqueEquipamentoWithValidData() throws EstoqueEquipamentoNotFoundException, HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        IEstoqueEquipamentoUpdateData dados = mock(IEstoqueEquipamentoUpdateData.class);
        EstoqueEquipamentoSchema estoqueSchema = mock(EstoqueEquipamentoSchema.class);
        EstoqueEquipamento estoque = mock(EstoqueEquipamento.class);
        EquipamentoSchema equipamentoSchema = mock(EquipamentoSchema.class);
        Equipamento equipamento = mock(Equipamento.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);

        List<UUID> itensIds = List.of(UUID.randomUUID());
        List<Long> quantidades = List.of(5L);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoqueSchema));
        when(estoqueSchema.toEstoqueEquipamento()).thenReturn(estoque);

        when(equipamentoGateway.findAll()).thenReturn(List.of(equipamentoSchema));
        when(equipamentoSchema.getId()).thenReturn(itensIds.get(0));
        when(equipamentoSchema.toEquipamento()).thenReturn(equipamento);

        when(dados.itens()).thenReturn(itensIds);
        when(dados.quantidades()).thenReturn(quantidades);
        when(dados.hospital()).thenReturn(UUID.randomUUID());

        when(hospitalGateway.findById(any())).thenReturn(Optional.of(hospitalSchema));
        when(estoqueGateway.update(any(), any(), any())).thenReturn(estoqueSchema);
        when(estoqueSchema.toEstoqueEquipamento()).thenReturn(estoque);

        EstoqueEquipamento resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(estoqueGateway, times(1)).findById(id);
        verify(equipamentoGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).findById(any());
        verify(estoqueGateway, times(1)).update(any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar EstoqueEquipamentoNotFoundException quando estoque não existe")
    void shouldThrowEstoqueEquipamentoNotFoundExceptionWhenEstoqueDoesNotExist() {
        UUID id = UUID.randomUUID();
        IEstoqueEquipamentoUpdateData dados = mock(IEstoqueEquipamentoUpdateData.class);

        when(estoqueGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EstoqueEquipamentoNotFoundException.class, () -> useCase.execute(id, dados));
        verify(estoqueGateway, times(1)).findById(id);
        verify(equipamentoGateway, never()).findAll();
        verify(hospitalGateway, never()).findById(any());
        verify(estoqueGateway, never()).update(any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID id = UUID.randomUUID();
        IEstoqueEquipamentoUpdateData dados = mock(IEstoqueEquipamentoUpdateData.class);
        EstoqueEquipamentoSchema estoqueSchema = mock(EstoqueEquipamentoSchema.class);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoqueSchema));
        when(estoqueSchema.toEstoqueEquipamento()).thenReturn(mock(EstoqueEquipamento.class));
        when(equipamentoGateway.findAll()).thenReturn(List.of());
        when(dados.itens()).thenReturn(List.of());
        when(dados.hospital()).thenReturn(UUID.randomUUID());
        when(hospitalGateway.findById(any())).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(id, dados));
        verify(hospitalGateway, times(1)).findById(any());
        verify(estoqueGateway, never()).update(any(), any(), any());
    }

    @Test
    @DisplayName("Deve ignorar atualização de itens e quantidades quando listas estão vazias ou nulas")
    void shouldIgnoreUpdateOfItensAndQuantidadesWhenListsAreEmptyOrNull() throws EstoqueEquipamentoNotFoundException, HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        IEstoqueEquipamentoUpdateData dados = mock(IEstoqueEquipamentoUpdateData.class);
        EstoqueEquipamentoSchema estoqueSchema = mock(EstoqueEquipamentoSchema.class);
        EstoqueEquipamento estoque = mock(EstoqueEquipamento.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoqueSchema));
        when(estoqueSchema.toEstoqueEquipamento()).thenReturn(estoque);
        when(equipamentoGateway.findAll()).thenReturn(List.of());
        when(dados.itens()).thenReturn(List.of());
        when(dados.quantidades()).thenReturn(null);
        when(dados.hospital()).thenReturn(UUID.randomUUID());
        when(hospitalGateway.findById(any())).thenReturn(Optional.of(hospitalSchema));
        when(estoqueGateway.update(any(), any(), any())).thenReturn(estoqueSchema);
        when(estoqueSchema.toEstoqueEquipamento()).thenReturn(estoque);

        EstoqueEquipamento resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(estoqueGateway, times(1)).findById(id);
        verify(equipamentoGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).findById(any());
        verify(estoqueGateway, times(1)).update(any(), any(), any());
    }
}