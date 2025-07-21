package com.fiap.hackaton.usecase.colaborador;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchColaboradorUseCaseTest {

    private ColaboradorGateway colaboradorGateway;
    private SearchColaboradorUseCase useCase;

    @BeforeEach
    void setUp() {
        colaboradorGateway = mock(ColaboradorGateway.class);
        useCase = new SearchColaboradorUseCase(colaboradorGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de colaboradores quando existirem colaboradores")
    void shouldReturnListOfColaboradoresWhenColaboradoresExist() {
        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorGateway.findAll()).thenReturn(List.of(colaborador));

        List<Colaborador> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(colaboradorGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem colaboradores")
    void shouldReturnEmptyListWhenNoColaboradoresExist() {
        when(colaboradorGateway.findAll()).thenReturn(Collections.emptyList());

        List<Colaborador> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(colaboradorGateway, times(1)).findAll();
    }
}