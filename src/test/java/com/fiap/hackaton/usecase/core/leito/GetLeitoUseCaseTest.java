package com.fiap.hackaton.usecase.core.leito;

import com.fiap.hackaton.entity.core.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.leito.model.Leito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetLeitoUseCaseTest {

    private LeitoGateway leitoGateway;
    private GetLeitoUseCase useCase;

    @BeforeEach
    void setUp() {
        leitoGateway = mock(LeitoGateway.class);
        useCase = new GetLeitoUseCase(leitoGateway);
    }

    @Test
    @DisplayName("Deve retornar leito quando encontrado pelo id")
    void shouldReturnLeitoWhenFoundById() throws LeitoNotFoundException {
        UUID id = UUID.randomUUID();
        Leito leito = mock(Leito.class);

        when(leitoGateway.findById(id)).thenReturn(Optional.of(leito));

        Leito resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(leito, resultado);
        verify(leitoGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar LeitoNotFoundException quando leito não existe")
    void shouldThrowLeitoNotFoundExceptionWhenLeitoDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(leitoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(LeitoNotFoundException.class, () -> useCase.execute(id));
        verify(leitoGateway, times(1)).findById(id);
    }
}