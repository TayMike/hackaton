package com.fiap.hackaton.usecase.entregaEquipamento;

import com.fiap.hackaton.entity.entregaEquipamento.exception.EntregaEquipamentoNotFoundException;
import com.fiap.hackaton.entity.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetEntregaEquipamentoUseCaseTest {

    private EntregaEquipamentoGateway entregaEquipamentoGateway;
    private GetEntregaEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        entregaEquipamentoGateway = mock(EntregaEquipamentoGateway.class);
        useCase = new GetEntregaEquipamentoUseCase(entregaEquipamentoGateway);
    }

    @Test
    @DisplayName("Deve retornar entrega de equipamento quando encontrado pelo id")
    void shouldReturnEntregaEquipamentoWhenFoundById() throws EntregaEquipamentoNotFoundException {
        UUID id = UUID.randomUUID();
        EntregaEquipamento entregaEquipamento = mock(EntregaEquipamento.class);

        when(entregaEquipamentoGateway.findById(id)).thenReturn(Optional.of(entregaEquipamento));

        EntregaEquipamento resultado = useCase.execute(id);

        assertNotNull(resultado);
        verify(entregaEquipamentoGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar EntregaEquipamentoNotFoundException quando entrega não existe")
    void shouldThrowEntregaEquipamentoNotFoundExceptionWhenEntregaDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(entregaEquipamentoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntregaEquipamentoNotFoundException.class, () -> useCase.execute(id));
        verify(entregaEquipamentoGateway, times(1)).findById(id);
    }
}