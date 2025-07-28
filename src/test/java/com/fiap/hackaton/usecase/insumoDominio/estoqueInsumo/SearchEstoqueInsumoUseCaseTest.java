package com.fiap.hackaton.usecase.insumoDominio.estoqueInsumo;

import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchEstoqueInsumoUseCaseTest {

    private EstoqueInsumoGateway estoqueGateway;
    private SearchEstoqueInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueInsumoGateway.class);
        useCase = new SearchEstoqueInsumoUseCase(estoqueGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de estoques de insumos quando existirem registros")
    void shouldReturnListOfEstoqueInsumoWhenRecordsExist() {
        EstoqueInsumo estoque = mock(EstoqueInsumo.class);
        when(estoqueGateway.findAll()).thenReturn(List.of(estoque));

        List<EstoqueInsumo> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(estoque, resultado.getFirst());
        verify(estoqueGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de estoques de insumos")
    void shouldReturnEmptyListWhenNoEstoqueInsumoExist() {
        when(estoqueGateway.findAll()).thenReturn(Collections.emptyList());

        List<EstoqueInsumo> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(estoqueGateway, times(1)).findAll();
    }
}