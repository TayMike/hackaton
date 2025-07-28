package com.fiap.hackaton.usecase.equipamentoDominio.equipamento;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetEquipamentoUseCaseTest {

    private EquipamentoGateway equipamentoGateway;
    private GetEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        equipamentoGateway = mock(EquipamentoGateway.class);
        useCase = new GetEquipamentoUseCase(equipamentoGateway);
    }

    @Test
    @DisplayName("Deve retornar equipamento quando encontrado pelo id")
    void shouldReturnEquipamentoWhenFoundById() throws EquipamentoNotFoundException {
        UUID id = UUID.randomUUID();
        Equipamento equipamento = mock(Equipamento.class);

        when(equipamentoGateway.findById(id)).thenReturn(Optional.of(equipamento));

        Equipamento resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(equipamento, resultado);
        verify(equipamentoGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar EquipamentoNotFoundException quando equipamento não existe")
    void shouldThrowEquipamentoNotFoundExceptionWhenEquipamentoDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(equipamentoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EquipamentoNotFoundException.class, () -> useCase.execute(id));
        verify(equipamentoGateway, times(1)).findById(id);
    }
}