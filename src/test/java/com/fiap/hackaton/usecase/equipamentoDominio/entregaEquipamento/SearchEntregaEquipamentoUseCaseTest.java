package com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento;

import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model.EntregaEquipamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchEntregaEquipamentoUseCaseTest {

    private EntregaEquipamentoGateway entregaEquipamentoGateway;
    private SearchEntregaEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        entregaEquipamentoGateway = mock(EntregaEquipamentoGateway.class);
        useCase = new SearchEntregaEquipamentoUseCase(entregaEquipamentoGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de entregas de equipamentos quando existirem registros")
    void shouldReturnListOfEntregaEquipamentoWhenRecordsExist() {
        EntregaEquipamento entrega = mock(EntregaEquipamento.class);
        when(entregaEquipamentoGateway.findAll()).thenReturn(List.of(entrega));

        List<EntregaEquipamento> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(entrega, resultado.getFirst());
        verify(entregaEquipamentoGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de entregas de equipamentos")
    void shouldReturnEmptyListWhenNoEntregaEquipamentoExist() {
        when(entregaEquipamentoGateway.findAll()).thenReturn(Collections.emptyList());

        List<EntregaEquipamento> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(entregaEquipamentoGateway, times(1)).findAll();
    }
}