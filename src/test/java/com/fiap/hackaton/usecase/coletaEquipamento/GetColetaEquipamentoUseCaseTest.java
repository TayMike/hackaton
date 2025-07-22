package com.fiap.hackaton.usecase.coletaEquipamento;

import com.fiap.hackaton.entity.coletaEquipamento.exception.ColetaEquipamentoNotFoundException;
import com.fiap.hackaton.entity.coletaEquipamento.gateway.ColetaEquipamentoGateway;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.ColetaEquipamentoSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetColetaEquipamentoUseCaseTest {

    private ColetaEquipamentoGateway coletaEquipamentoGateway;
    private GetColetaEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        coletaEquipamentoGateway = mock(ColetaEquipamentoGateway.class);
        useCase = new GetColetaEquipamentoUseCase(coletaEquipamentoGateway);
    }

    @Test
    @DisplayName("Deve retornar coleta de equipamento quando encontrado pelo id")
    void shouldReturnColetaEquipamentoWhenFoundById() throws ColetaEquipamentoNotFoundException {
        UUID id = UUID.randomUUID();
        ColetaEquipamentoSchema coletaEquipamentoSchema = mock(ColetaEquipamentoSchema.class);
        ColetaEquipamento coletaEquipamento = mock(ColetaEquipamento.class);

        when(coletaEquipamentoGateway.findById(id)).thenReturn(Optional.of(coletaEquipamentoSchema));
        when(coletaEquipamentoSchema.toColetaEquipamento()).thenReturn(coletaEquipamento);

        ColetaEquipamento resultado = useCase.execute(id);

        assertNotNull(resultado);
        verify(coletaEquipamentoGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar ColetaEquipamentoNotFoundException quando coleta não existe")
    void shouldThrowColetaEquipamentoNotFoundExceptionWhenColetaDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(coletaEquipamentoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(ColetaEquipamentoNotFoundException.class, () -> useCase.execute(id));
        verify(coletaEquipamentoGateway, times(1)).findById(id);
    }
}