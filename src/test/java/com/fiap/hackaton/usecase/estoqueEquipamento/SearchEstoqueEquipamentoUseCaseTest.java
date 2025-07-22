package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueEquipamentoSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchEstoqueEquipamentoUseCaseTest {

    private EstoqueEquipamentoGateway estoqueGateway;
    private SearchEstoqueEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueEquipamentoGateway.class);
        useCase = new SearchEstoqueEquipamentoUseCase(estoqueGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de estoques de equipamentos quando existirem registros")
    void shouldReturnListOfEstoqueEquipamentoWhenRecordsExist() {
        EstoqueEquipamentoSchema estoqueSchema = mock(EstoqueEquipamentoSchema.class);
        EstoqueEquipamento estoque = mock(EstoqueEquipamento.class);
        when(estoqueGateway.findAll()).thenReturn(List.of(estoqueSchema));
        when(estoqueSchema.toEstoqueEquipamento()).thenReturn(estoque);

        List<EstoqueEquipamento> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(estoque, resultado.get(0));
        verify(estoqueGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de estoques de equipamentos")
    void shouldReturnEmptyListWhenNoEstoqueEquipamentoExist() {
        when(estoqueGateway.findAll()).thenReturn(Collections.emptyList());

        List<EstoqueEquipamento> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(estoqueGateway, times(1)).findAll();
    }
}