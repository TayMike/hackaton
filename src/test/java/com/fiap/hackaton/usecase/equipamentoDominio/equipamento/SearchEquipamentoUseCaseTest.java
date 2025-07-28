package com.fiap.hackaton.usecase.equipamentoDominio.equipamento;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchEquipamentoUseCaseTest {

    private EquipamentoGateway equipamentoGateway;
    private SearchEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        equipamentoGateway = mock(EquipamentoGateway.class);
        useCase = new SearchEquipamentoUseCase(equipamentoGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de equipamentos quando existirem registros")
    void shouldReturnListOfEquipamentoWhenRecordsExist() {
        Equipamento equipamento = mock(Equipamento.class);
        when(equipamentoGateway.findAll()).thenReturn(List.of(equipamento));

        List<Equipamento> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(equipamento, resultado.get(0));
        verify(equipamentoGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de equipamentos")
    void shouldReturnEmptyListWhenNoEquipamentoExist() {
        when(equipamentoGateway.findAll()).thenReturn(Collections.emptyList());

        List<Equipamento> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(equipamentoGateway, times(1)).findAll();
    }
}