package com.fiap.hackaton.usecase.colaborador;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.usecase.colaborador.GetColaboradorUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;

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
        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(id)).thenReturn(Optional.of(colaboradorSchema));

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador);

        Colaborador resultado = useCase.execute(id);

        assertNotNull(resultado);
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