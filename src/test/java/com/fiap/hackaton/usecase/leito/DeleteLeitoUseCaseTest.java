package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.infrastructure.config.db.schema.LeitoSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteLeitoUseCaseTest {

    private LeitoGateway leitoGateway;
    private DeleteLeitoUseCase useCase;

    @BeforeEach
    void setUp() {
        leitoGateway = mock(LeitoGateway.class);
        useCase = new DeleteLeitoUseCase(leitoGateway);
    }

    @Test
    @DisplayName("Deve deletar leito existente e retornar o leito deletado")
    void shouldDeleteExistingLeitoAndReturnDeletedLeito() throws LeitoNotFoundException {
        UUID id = UUID.randomUUID();
        LeitoSchema leitoSchema = mock(LeitoSchema.class);
        Leito leito = mock(Leito.class);

        when(leitoGateway.findById(id)).thenReturn(Optional.of(leitoSchema));
        when(leitoSchema.toLeito()).thenReturn(leito);

        Leito resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(leito, resultado);
        verify(leitoGateway, times(1)).findById(id);
        verify(leitoGateway, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar LeitoNotFoundException quando leito não existe")
    void shouldThrowLeitoNotFoundExceptionWhenLeitoDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(leitoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(LeitoNotFoundException.class, () -> useCase.execute(id));
        verify(leitoGateway, times(1)).findById(id);
        verify(leitoGateway, never()).deleteById(id);
    }
}