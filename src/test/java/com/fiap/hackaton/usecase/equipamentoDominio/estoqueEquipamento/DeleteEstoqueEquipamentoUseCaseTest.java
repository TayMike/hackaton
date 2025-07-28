package com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteEstoqueEquipamentoUseCaseTest {

    private EstoqueEquipamentoGateway estoqueGateway;
    private DeleteEstoqueEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueEquipamentoGateway.class);
        useCase = new DeleteEstoqueEquipamentoUseCase(estoqueGateway);
    }

    @Test
    @DisplayName("Deve deletar estoque de equipamento existente")
    void shouldDeleteExistingEstoqueEquipamento() throws EstoqueEquipamentoNotFoundException {
        UUID id = UUID.randomUUID();

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(mock(EstoqueEquipamento.class)));

        useCase.execute(id);

        verify(estoqueGateway, times(1)).findById(id);
        verify(estoqueGateway, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar EstoqueEquipamentoNotFoundException quando estoque não existe")
    void shouldThrowEstoqueEquipamentoNotFoundExceptionWhenEstoqueDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(estoqueGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EstoqueEquipamentoNotFoundException.class, () -> useCase.execute(id));

        verify(estoqueGateway, times(1)).findById(id);
        verify(estoqueGateway, never()).deleteById(id);
    }
}