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

class DeleteEstoqueInsumoUseCaseTest {

    private EstoqueInsumoGateway estoqueGateway;
    private DeleteEstoqueInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueInsumoGateway.class);
        useCase = new DeleteEstoqueInsumoUseCase(estoqueGateway);
    }

    @Test
    @DisplayName("Deve deletar estoque de insumo existente e retornar o estoque deletado")
    void shouldDeleteExistingEstoqueInsumoAndReturnDeletedEstoque() throws EstoqueInsumoNotFoundException {
        UUID id = UUID.randomUUID();
        EstoqueInsumoSchema estoqueSchema = mock(EstoqueInsumoSchema.class);
        EstoqueInsumo estoque = mock(EstoqueInsumo.class);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoqueSchema));
        when(estoqueSchema.toEstoqueInsumo()).thenReturn(estoque);

        useCase.execute(id);

        verify(estoqueGateway, times(1)).findById(id);
        verify(estoqueGateway, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar EstoqueInsumoNotFoundException quando estoque não existe")
    void shouldThrowEstoqueInsumoNotFoundExceptionWhenEstoqueDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(estoqueGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EstoqueInsumoNotFoundException.class, () -> useCase.execute(id));
        verify(estoqueGateway, times(1)).findById(id);
        verify(estoqueGateway, never()).deleteById(id);
    }
}