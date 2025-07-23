package com.fiap.hackaton.usecase.coletaInsumo;
import com.fiap.hackaton.entity.coletaInsumo.exception.ColetaInsumoNotFoundException;
import com.fiap.hackaton.entity.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetColetaInsumoUseCaseTest {

    private ColetaInsumoGateway coletaInsumoGateway;
    private GetColetaInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        coletaInsumoGateway = mock(ColetaInsumoGateway.class);
        useCase = new GetColetaInsumoUseCase(coletaInsumoGateway);
    }

    @Test
    @DisplayName("Deve retornar coleta de insumo quando encontrado pelo id")
    void shouldReturnColetaInsumoWhenFoundById() throws ColetaInsumoNotFoundException {
        UUID id = UUID.randomUUID();
        ColetaInsumo coletaInsumo = mock(ColetaInsumo.class);
        var coletaInsumoSchema = mock(com.fiap.hackaton.infrastructure.config.db.schema.ColetaInsumoSchema.class);
        when(coletaInsumoGateway.findById(id)).thenReturn(Optional.of(coletaInsumoSchema));
        when(coletaInsumoSchema.toColeta()).thenReturn(coletaInsumo);

        ColetaInsumo resultado = useCase.execute(id);

        assertNotNull(resultado);
        verify(coletaInsumoGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar ColetaInsumoNotFoundException quando coleta de insumo não existe")
    void shouldThrowColetaInsumoNotFoundExceptionWhenColetaInsumoDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(coletaInsumoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(ColetaInsumoNotFoundException.class, () -> useCase.execute(id));
        verify(coletaInsumoGateway, times(1)).findById(id);
    }
}