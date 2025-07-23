package com.fiap.hackaton.usecase.insumo;

import com.fiap.hackaton.entity.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteInsumoUseCaseTest {

    private InsumoGateway insumoGateway;
    private DeleteInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        insumoGateway = mock(InsumoGateway.class);
        useCase = new DeleteInsumoUseCase(insumoGateway);
    }

    @Test
    @DisplayName("Deve deletar insumo existente e retornar o insumo deletado")
    void shouldDeleteExistingInsumoAndReturnDeletedInsumo() throws InsumoNotFoundException {
        UUID id = UUID.randomUUID();
        Insumo insumo = mock(Insumo.class);
        var insumoSchema = mock(com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema.class);

        when(insumoGateway.findById(id)).thenReturn(Optional.of(insumoSchema));
        when(insumoSchema.toInsumo()).thenReturn(insumo);

        useCase.execute(id);

        verify(insumoGateway, times(1)).findById(id);
        verify(insumoGateway, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar InsumoNotFoundException quando insumo não existe")
    void shouldThrowInsumoNotFoundExceptionWhenInsumoDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(insumoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(InsumoNotFoundException.class, () -> useCase.execute(id));
        verify(insumoGateway, times(1)).findById(id);
        verify(insumoGateway, never()).deleteById(id);
    }
}