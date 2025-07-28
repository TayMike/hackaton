package com.fiap.hackaton.usecase.insumoDominio.entregaInsumo;

import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.exception.EntregaInsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class GetEntregaInsumoUseCaseTest {

    private EntregaInsumoGateway entregaInsumoGateway;
    private GetEntregaInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        entregaInsumoGateway = mock(EntregaInsumoGateway.class);
        useCase = new GetEntregaInsumoUseCase(entregaInsumoGateway);
    }

    @Test
    @DisplayName("Deve retornar entrega de insumo quando encontrado pelo id")
    void shouldReturnEntregaInsumoWhenFoundById() throws EntregaInsumoNotFoundException {
        UUID id = UUID.randomUUID();
        EntregaInsumo entregaInsumo = mock(EntregaInsumo.class);

        when(entregaInsumoGateway.findById(id)).thenReturn(Optional.of(entregaInsumo));

        EntregaInsumo resultado = useCase.execute(id);

        assertNotNull(resultado);
        verify(entregaInsumoGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar EntregaInsumoNotFoundException quando entrega não existe")
    void shouldThrowEntregaInsumoNotFoundExceptionWhenEntregaDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(entregaInsumoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntregaInsumoNotFoundException.class, () -> useCase.execute(id));
        verify(entregaInsumoGateway, times(1)).findById(id);
    }
}