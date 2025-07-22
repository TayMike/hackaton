package com.fiap.hackaton.usecase.descarteEquipamento;

import com.fiap.hackaton.entity.descarteEquipamento.gateway.DescarteEquipamentoGateway;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchDescarteEquipamentoUseCaseTest {

    private DescarteEquipamentoGateway descarteEquipamentoGateway;
    private SearchDescarteEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        descarteEquipamentoGateway = mock(DescarteEquipamentoGateway.class);
        useCase = new SearchDescarteEquipamentoUseCase(descarteEquipamentoGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de descarte de equipamentos quando existirem registros")
    void shouldReturnListOfDescarteEquipamentoWhenRecordsExist() {
        DescarteEquipamento descarteEquipamento = mock(DescarteEquipamento.class);
        when(descarteEquipamentoGateway.findAll()).thenReturn(List.of(descarteEquipamento));

        List<DescarteEquipamento> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(descarteEquipamentoGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de descarte de equipamentos")
    void shouldReturnEmptyListWhenNoDescarteEquipamentoExist() {
        when(descarteEquipamentoGateway.findAll()).thenReturn(Collections.emptyList());

        List<DescarteEquipamento> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(descarteEquipamentoGateway, times(1)).findAll();
    }
}