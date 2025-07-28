package com.fiap.hackaton.usecase.insumoDominio.insumo;

import com.fiap.hackaton.entity.insumoDominio.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("Deve deletar insumo existente e verificar chamadas")
    void shouldDeleteExistingInsumoAndVerifyCalls() throws InsumoNotFoundException {
        UUID id = UUID.randomUUID();
        Insumo insumo = mock(Insumo.class);

        when(insumoGateway.findById(id)).thenReturn(Optional.of(insumo));

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