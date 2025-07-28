package com.fiap.hackaton.usecase.insumoDominio.insumo;

import com.fiap.hackaton.entity.insumoDominio.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetInsumoUseCaseTest {

    private InsumoGateway insumoGateway;
    private GetInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        insumoGateway = mock(InsumoGateway.class);
        useCase = new GetInsumoUseCase(insumoGateway);
    }

    @Test
    @DisplayName("Deve retornar insumo quando encontrado pelo id")
    void shouldReturnInsumoWhenFoundById() throws InsumoNotFoundException {
        UUID id = UUID.randomUUID();
        Insumo insumo = mock(Insumo.class);

        when(insumoGateway.findById(id)).thenReturn(Optional.of(insumo));

        Insumo resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(insumo, resultado);
        verify(insumoGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar InsumoNotFoundException quando insumo não existe")
    void shouldThrowInsumoNotFoundExceptionWhenInsumoDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(insumoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(InsumoNotFoundException.class, () -> useCase.execute(id));
        verify(insumoGateway, times(1)).findById(id);
    }
}