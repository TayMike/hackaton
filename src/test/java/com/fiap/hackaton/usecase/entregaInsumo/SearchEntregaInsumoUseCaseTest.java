package com.fiap.hackaton.usecase.entregaInsumo;

import com.fiap.hackaton.entity.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchEntregaInsumoUseCaseTest {

    private EntregaInsumoGateway entregaInsumoGateway;
    private SearchEntregaInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        entregaInsumoGateway = mock(EntregaInsumoGateway.class);
        useCase = new SearchEntregaInsumoUseCase(entregaInsumoGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de entregas de insumos quando existirem registros")
    void shouldReturnListOfEntregaInsumoWhenRecordsExist() {
        EntregaInsumo entregaInsumo = mock(EntregaInsumo.class);
        when(entregaInsumoGateway.findAll()).thenReturn(List.of(entregaInsumo));

        List<EntregaInsumo> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(entregaInsumoGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de entregas de insumos")
    void shouldReturnEmptyListWhenNoEntregaInsumoExist() {
        when(entregaInsumoGateway.findAll()).thenReturn(Collections.emptyList());

        List<EntregaInsumo> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(entregaInsumoGateway, times(1)).findAll();
    }
}