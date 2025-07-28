package com.fiap.hackaton.usecase.insumoDominio.insumo;

import com.fiap.hackaton.entity.insumoDominio.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import com.fiap.hackaton.usecase.insumoDominio.insumo.dto.IInsumoUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateInsumoUseCaseTest {

    private InsumoGateway insumoGateway;
    private UpdateInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        insumoGateway = mock(InsumoGateway.class);
        useCase = new UpdateInsumoUseCase(insumoGateway);
    }

    @Test
    @DisplayName("Deve atualizar todos os campos do insumo quando dados válidos são fornecidos")
    void shouldUpdateAllFieldsWhenValidDataProvided() throws InsumoNotFoundException {
        UUID id = UUID.randomUUID();
        IInsumoUpdateData dados = mock(IInsumoUpdateData.class);
        Insumo insumo = mock(Insumo.class);

        when(insumoGateway.findById(id)).thenReturn(Optional.of(insumo));
        when(insumoGateway.update(insumo)).thenReturn(insumo);

        when(dados.nome()).thenReturn("Novo Nome");
        when(dados.custo()).thenReturn(new BigDecimal("10.50"));
        when(dados.peso()).thenReturn(100L);
        when(dados.validade()).thenReturn(OffsetDateTime.now());
        when(dados.marca()).thenReturn("Nova Marca");
        when(dados.unidadeMedida()).thenReturn(Insumo.Medida.MG);

        Insumo resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(insumo).setNome("Novo Nome");
        verify(insumo).setCusto(new BigDecimal("10.50"));
        verify(insumo).setPeso(100L);
        verify(insumo).setValidade(any(OffsetDateTime.class));
        verify(insumo).setMarca("Nova Marca");
        verify(insumo).setUnidadeMedida(Insumo.Medida.MG);
        verify(insumoGateway, times(1)).update(insumo);
    }

    @Test
    @DisplayName("Deve lançar InsumoNotFoundException quando insumo não existe")
    void shouldThrowInsumoNotFoundExceptionWhenInsumoDoesNotExist() {
        UUID id = UUID.randomUUID();
        IInsumoUpdateData dados = mock(IInsumoUpdateData.class);

        when(insumoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(InsumoNotFoundException.class, () -> useCase.execute(id, dados));
        verify(insumoGateway, times(1)).findById(id);
        verify(insumoGateway, never()).update(any());
    }

    @Test
    @DisplayName("Deve ignorar atualização de campos quando valores nulos ou inválidos são fornecidos")
    void shouldIgnoreUpdateWhenFieldsAreNullOrInvalid() throws InsumoNotFoundException {
        UUID id = UUID.randomUUID();
        IInsumoUpdateData dados = mock(IInsumoUpdateData.class);
        Insumo insumo = mock(Insumo.class);

        when(insumoGateway.findById(id)).thenReturn(Optional.of(insumo));
        when(insumoGateway.update(insumo)).thenReturn(insumo);

        when(dados.nome()).thenReturn(" ");
        when(dados.custo()).thenReturn(BigDecimal.ZERO);
        when(dados.peso()).thenReturn(0L);
        when(dados.validade()).thenReturn(null);
        when(dados.marca()).thenReturn("");
        when(dados.unidadeMedida()).thenReturn(null);

        Insumo resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(insumo, never()).setNome(any());
        verify(insumo, never()).setCusto(any());
        verify(insumo, never()).setPeso(any());
        verify(insumo, never()).setValidade(any());
        verify(insumo, never()).setMarca(any());
        verify(insumo, never()).setUnidadeMedida(any());
        verify(insumoGateway, times(1)).update(insumo);
    }

    @Test
    @DisplayName("Deve atualizar apenas campos não nulos e válidos")
    void shouldUpdateOnlyNonNullAndValidFields() throws InsumoNotFoundException {
        UUID id = UUID.randomUUID();
        IInsumoUpdateData dados = mock(IInsumoUpdateData.class);
        Insumo insumo = mock(Insumo.class);

        when(insumoGateway.findById(id)).thenReturn(Optional.of(insumo));
        when(insumoGateway.update(insumo)).thenReturn(insumo);

        when(dados.nome()).thenReturn("Novo Nome");
        when(dados.custo()).thenReturn(null);
        when(dados.peso()).thenReturn(0L);
        when(dados.validade()).thenReturn(null);
        when(dados.marca()).thenReturn("Nova Marca");
        when(dados.unidadeMedida()).thenReturn(null);

        Insumo resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(insumo).setNome("Novo Nome");
        verify(insumo, never()).setCusto(any());
        verify(insumo, never()).setPeso(any());
        verify(insumo, never()).setValidade(any());
        verify(insumo).setMarca("Nova Marca");
        verify(insumo, never()).setUnidadeMedida(any());
        verify(insumoGateway, times(1)).update(insumo);
    }
}