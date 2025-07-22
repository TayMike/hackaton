package com.fiap.hackaton.usecase.descarteEquipamento;

import com.fiap.hackaton.entity.descarteEquipamento.exception.DescarteEquipamentoNotFoundException;
import com.fiap.hackaton.entity.descarteEquipamento.gateway.DescarteEquipamentoGateway;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetDescarteEquipamentoUseCaseTest {

    private DescarteEquipamentoGateway descarteEquipamentoGateway;
    private GetDescarteEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        descarteEquipamentoGateway = mock(DescarteEquipamentoGateway.class);
        useCase = new GetDescarteEquipamentoUseCase(descarteEquipamentoGateway);
    }

    @Test
    @DisplayName("Deve retornar descarte de equipamento quando encontrado pelo id")
    void shouldReturnDescarteEquipamentoWhenFoundById() throws DescarteEquipamentoNotFoundException {
        UUID id = UUID.randomUUID();
        DescarteEquipamento descarteEquipamento = mock(DescarteEquipamento.class);

        when(descarteEquipamentoGateway.findById(id)).thenReturn(Optional.of(descarteEquipamento));

        DescarteEquipamento resultado = useCase.execute(id);

        assertNotNull(resultado);
        verify(descarteEquipamentoGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar DescarteEquipamentoNotFoundException quando descarte não existe")
    void shouldThrowDescarteEquipamentoNotFoundExceptionWhenDescarteDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(descarteEquipamentoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(DescarteEquipamentoNotFoundException.class, () -> useCase.execute(id));
        verify(descarteEquipamentoGateway, times(1)).findById(id);
    }
}