package com.fiap.hackaton.usecase.insumo;

import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;
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
        InsumoSchema insumoSchema = mock(InsumoSchema.class);
        Insumo insumo = mock(Insumo.class);
        when(insumoGateway.findAll()).thenReturn(List.of(insumoSchema));
        when(insumoSchema.toInsumo()).thenReturn(insumo);

        List<Insumo> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(insumo, resultado.get(0));
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