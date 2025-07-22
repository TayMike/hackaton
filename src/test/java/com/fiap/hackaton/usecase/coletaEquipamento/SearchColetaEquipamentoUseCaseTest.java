package com.fiap.hackaton.usecase.coletaEquipamento;

import com.fiap.hackaton.entity.coletaEquipamento.gateway.ColetaEquipamentoGateway;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.ColetaEquipamentoSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchColetaEquipamentoUseCaseTest {

    private ColetaEquipamentoGateway coletaEquipamentoGateway;
    private SearchColetaEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        coletaEquipamentoGateway = mock(ColetaEquipamentoGateway.class);
        useCase = new SearchColetaEquipamentoUseCase(coletaEquipamentoGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de coleta de equipamentos quando existirem registros")
    void shouldReturnListOfColetaEquipamentoWhenRecordsExist() {
        ColetaEquipamentoSchema coletaEquipamentoSchema = mock(ColetaEquipamentoSchema.class);
        ColetaEquipamento coletaEquipamento = mock(ColetaEquipamento.class);
        when(coletaEquipamentoGateway.findAll()).thenReturn(List.of(coletaEquipamentoSchema));
        when(coletaEquipamentoSchema.toColetaEquipamento()).thenReturn(coletaEquipamento);

        List<ColetaEquipamento> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(coletaEquipamentoGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de coleta de equipamentos")
    void shouldReturnEmptyListWhenNoColetaEquipamentoExist() {
        when(coletaEquipamentoGateway.findAll()).thenReturn(Collections.emptyList());

        List<ColetaEquipamento> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(coletaEquipamentoGateway, times(1)).findAll();
    }
}