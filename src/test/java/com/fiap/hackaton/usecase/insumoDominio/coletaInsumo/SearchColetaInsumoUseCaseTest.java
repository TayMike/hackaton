package com.fiap.hackaton.usecase.insumoDominio.coletaInsumo;

import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchColetaInsumoUseCaseTest {

    private ColetaInsumoGateway coletaInsumoGateway;
    private SearchColetaInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        coletaInsumoGateway = mock(ColetaInsumoGateway.class);
        useCase = new SearchColetaInsumoUseCase(coletaInsumoGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de coleta de insumos quando existirem registros")
    void shouldReturnListOfColetaInsumosWhenRecordsExist() {
        ColetaInsumo coletaInsumo = mock(ColetaInsumo.class);
        when(coletaInsumoGateway.findAll()).thenReturn(List.of(coletaInsumo));

        List<ColetaInsumo> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(coletaInsumo, resultado.getFirst());
        verify(coletaInsumoGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de coleta de insumos")
    void shouldReturnEmptyListWhenNoColetaInsumosExist() {
        when(coletaInsumoGateway.findAll()).thenReturn(Collections.emptyList());

        List<ColetaInsumo> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(coletaInsumoGateway, times(1)).findAll();
    }
}