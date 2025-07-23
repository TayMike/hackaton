package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueInsumoSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetEstoqueInsumoUseCaseTest {

    private EstoqueInsumoGateway estoqueGateway;
    private GetEstoqueInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueInsumoGateway.class);
        useCase = new GetEstoqueInsumoUseCase(estoqueGateway);
    }

    @Test
    @DisplayName("Deve retornar estoque de insumo quando encontrado pelo id")
    void shouldReturnEstoqueInsumoWhenFoundById() throws EstoqueInsumoNotFoundException {
        UUID id = UUID.randomUUID();
        EstoqueInsumoSchema estoqueSchema = mock(EstoqueInsumoSchema.class);
        EstoqueInsumo estoque = mock(EstoqueInsumo.class);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoqueSchema));
        when(estoqueSchema.toEstoqueInsumo()).thenReturn(estoque);

        EstoqueInsumo resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(estoque, resultado);
        verify(estoqueGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar EstoqueInsumoNotFoundException quando estoque não existe")
    void shouldThrowEstoqueInsumoNotFoundExceptionWhenEstoqueDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(estoqueGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EstoqueInsumoNotFoundException.class, () -> useCase.execute(id));
        verify(estoqueGateway, times(1)).findById(id);
    }
}