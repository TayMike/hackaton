package com.fiap.hackaton.usecase.insumoDominio.insumo;

import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchInsumoUseCaseTest {

    private InsumoGateway insumoGateway;
    private SearchInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        insumoGateway = mock(InsumoGateway.class);
        useCase = new SearchInsumoUseCase(insumoGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de insumos quando existirem registros")
    void shouldReturnListOfInsumosWhenRecordsExist() {
        Insumo insumo = mock(Insumo.class);
        when(insumoGateway.findAll()).thenReturn(List.of(insumo));

        List<Insumo> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(insumo, resultado.getFirst());
        verify(insumoGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de insumos")
    void shouldReturnEmptyListWhenNoInsumosExist() {
        when(insumoGateway.findAll()).thenReturn(Collections.emptyList());

        List<Insumo> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(insumoGateway, times(1)).findAll();
    }
}