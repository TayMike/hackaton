package com.fiap.hackaton.usecase.core.leito;

import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.leito.model.Leito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchLeitoUseCaseTest {

    private LeitoGateway leitoGateway;
    private SearchLeitoUseCase useCase;

    @BeforeEach
    void setUp() {
        leitoGateway = mock(LeitoGateway.class);
        useCase = new SearchLeitoUseCase(leitoGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de leitos quando existirem registros")
    void shouldReturnListOfLeitosWhenRecordsExist() {
        Leito leito = mock(Leito.class);
        when(leitoGateway.findAll()).thenReturn(List.of(leito));

        List<Leito> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(leito, resultado.get(0));
        verify(leitoGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de leitos")
    void shouldReturnEmptyListWhenNoLeitosExist() {
        when(leitoGateway.findAll()).thenReturn(Collections.emptyList());

        List<Leito> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(leitoGateway, times(1)).findAll();
    }
}