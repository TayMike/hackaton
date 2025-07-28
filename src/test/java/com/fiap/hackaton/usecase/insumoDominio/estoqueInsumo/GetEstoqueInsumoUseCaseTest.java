package com.fiap.hackaton.usecase.insumoDominio.estoqueInsumo;

import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetEstoqueInsumoUseCaseTest {

    private EstoqueInsumoGateway estoqueGateway;
    private GetEstoqueInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueInsumoGateway.class);
        useCase = new GetEstoqueInsumoUseCase(estoqueGateway);
    }

    @Test
    @DisplayName("Deve retornar estoque de insumo quando encontrado pelo id")
    void shouldReturnEstoqueInsumoWhenFoundById() throws EstoqueInsumoNotFoundException {
        UUID id = UUID.randomUUID();
        EstoqueInsumo estoque = mock(EstoqueInsumo.class);

        when(estoqueGateway.findByHospitalId(id)).thenReturn(Optional.of(estoque));

        EstoqueInsumo resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(estoque, resultado);
        verify(estoqueGateway, times(1)).findByHospitalId(id);
    }

    @Test
    @DisplayName("Deve lançar EstoqueInsumoNotFoundException quando estoque não existe")
    void shouldThrowEstoqueInsumoNotFoundExceptionWhenEstoqueDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(estoqueGateway.findByHospitalId(id)).thenReturn(Optional.empty());

        assertThrows(EstoqueInsumoNotFoundException.class, () -> useCase.execute(id));
        verify(estoqueGateway, times(1)).findByHospitalId(id);
    }
}