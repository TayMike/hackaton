package com.fiap.hackaton.usecase.equipamento;

import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteEquipamentoUseCaseTest {

    private EquipamentoGateway equipamentoGateway;
    private DeleteEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        equipamentoGateway = mock(EquipamentoGateway.class);
        useCase = new DeleteEquipamentoUseCase(equipamentoGateway);
    }

    @Test
    @DisplayName("Deve deletar equipamento existente e retornar o equipamento deletado")
    void shouldDeleteExistingEquipamentoAndReturnDeletedEquipamento() throws EquipamentoNotFoundException {
        UUID id = UUID.randomUUID();
        EquipamentoSchema equipamentoSchema = mock(EquipamentoSchema.class);
        Equipamento equipamento = mock(Equipamento.class);

        when(equipamentoGateway.findById(id)).thenReturn(Optional.of(equipamentoSchema));
        when(equipamentoSchema.toEquipamento()).thenReturn(equipamento);

        Equipamento resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(equipamento, resultado);
        verify(equipamentoGateway, times(1)).findById(id);
        verify(equipamentoGateway, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar EquipamentoNotFoundException quando equipamento não existe")
    void shouldThrowEquipamentoNotFoundExceptionWhenEquipamentoDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(equipamentoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EquipamentoNotFoundException.class, () -> useCase.execute(id));
        verify(equipamentoGateway, times(1)).findById(id);
        verify(equipamentoGateway, never()).deleteById(id);
    }
}