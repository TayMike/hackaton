package com.fiap.hackaton.usecase.core.colaborador;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetColaboradorUseCaseTest {

    private ColaboradorGateway colaboradorGateway;
    private GetColaboradorUseCase useCase;

    @BeforeEach
    void setUp() {
        colaboradorGateway = mock(ColaboradorGateway.class);
        useCase = new GetColaboradorUseCase(colaboradorGateway);
    }

    @Test
    @DisplayName("Deve retornar colaborador quando encontrado pelo id")
    void shouldReturnColaboradorWhenFoundById() throws ColaboradorNotFoundException {
        UUID id = UUID.randomUUID();
        Colaborador colaborador = mock(Colaborador.class);

        when(colaboradorGateway.findById(id)).thenReturn(Optional.of(colaborador));

        Colaborador resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(colaborador, resultado);
        verify(colaboradorGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar ColaboradorNotFoundException quando colaborador não existe")
    void shouldThrowColaboradorNotFoundExceptionWhenColaboradorDoesNotExist() {
        UUID id = UUID.randomUUID();

        when(colaboradorGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(id));
        verify(colaboradorGateway, times(1)).findById(id);
    }
}