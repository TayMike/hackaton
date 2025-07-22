package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueEquipamentoSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetEstoqueEquipamentoUseCaseTest {

    private EstoqueEquipamentoGateway estoqueGateway;
    private GetEstoqueEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueEquipamentoGateway.class);
        useCase = new GetEstoqueEquipamentoUseCase(estoqueGateway);
    }

    @Test
    @DisplayName("Deve retornar estoque de equipamento quando encontrado pelo id")
    void shouldReturnEstoqueEquipamentoWhenFoundById() throws EstoqueEquipamentoNotFoundException {
        UUID id = UUID.randomUUID();
        EstoqueEquipamentoSchema estoqueSchema = mock(EstoqueEquipamentoSchema.class);
        EstoqueEquipamento estoque = mock(EstoqueEquipamento.class);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoqueSchema));
        when(estoqueSchema.toEstoqueEquipamento()).thenReturn(estoque);

        EstoqueEquipamento resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(estoque, resultado);
        verify(estoqueGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar EstoqueEquipamentoNotFoundException quando estoque não existe")
    void shouldThrowEstoqueEquipamentoNotFoundExceptionWhenEstoqueDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(estoqueGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EstoqueEquipamentoNotFoundException.class, () -> useCase.execute(id));
        verify(estoqueGateway, times(1)).findById(id);
    }
}