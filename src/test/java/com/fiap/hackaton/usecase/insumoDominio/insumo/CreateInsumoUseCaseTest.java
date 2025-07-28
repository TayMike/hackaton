package com.fiap.hackaton.usecase.insumoDominio.insumo;

import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import com.fiap.hackaton.usecase.insumoDominio.insumo.dto.IInsumoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateInsumoUseCaseTest {

    private InsumoGateway insumoGateway;
    private CreateInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        insumoGateway = mock(InsumoGateway.class);
        useCase = new CreateInsumoUseCase(insumoGateway);
    }

    private OffsetDateTime toOffsetDateTime(LocalDate localDate) {
        return localDate.atStartOfDay().atOffset(ZoneOffset.UTC);
    }

    @Test
    @DisplayName("Deve criar insumo com dados válidos")
    void shouldCreateInsumoWithValidData() {
        IInsumoRegistrationData dados = mock(IInsumoRegistrationData.class);
        when(dados.nome()).thenReturn("Seringa");
        when(dados.custo()).thenReturn(BigDecimal.valueOf(5.0));
        when(dados.quantidade()).thenReturn(100L);
        when(dados.peso()).thenReturn(1L);
        when(dados.validade()).thenReturn(toOffsetDateTime(LocalDate.now().plusDays(30)));
        when(dados.marca()).thenReturn("MarcaX");
        when(dados.unidadeMedida()).thenReturn(Insumo.Medida.MG);

        Insumo insumo = mock(Insumo.class);
        when(insumoGateway.save(any(Insumo.class))).thenReturn(insumo);

        Insumo resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(insumoGateway, times(1)).save(any(Insumo.class));
    }

    @Test
    @DisplayName("Deve criar insumo mesmo com nome vazio")
    void shouldCreateInsumoWithEmptyName() {
        IInsumoRegistrationData dados = mock(IInsumoRegistrationData.class);
        when(dados.nome()).thenReturn("");
        when(dados.custo()).thenReturn(BigDecimal.valueOf(1.0));
        when(dados.quantidade()).thenReturn(1L);
        when(dados.peso()).thenReturn(1L);
        when(dados.validade()).thenReturn(toOffsetDateTime(LocalDate.now().plusDays(1)));
        when(dados.marca()).thenReturn("MarcaY");
        when(dados.unidadeMedida()).thenReturn(Insumo.Medida.MG);

        Insumo insumo = mock(Insumo.class);
        when(insumoGateway.save(any(Insumo.class))).thenReturn(insumo);

        Insumo resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(insumoGateway, times(1)).save(any(Insumo.class));
    }

    @Test
    @DisplayName("Deve criar insumo com quantidade zero")
    void shouldCreateInsumoWithZeroQuantity() {
        IInsumoRegistrationData dados = mock(IInsumoRegistrationData.class);
        when(dados.nome()).thenReturn("Álcool");
        when(dados.custo()).thenReturn(BigDecimal.valueOf(2.0));
        when(dados.quantidade()).thenReturn(0L);
        when(dados.peso()).thenReturn(1L);
        when(dados.validade()).thenReturn(toOffsetDateTime(LocalDate.now().plusDays(10)));
        when(dados.marca()).thenReturn("MarcaZ");
        when(dados.unidadeMedida()).thenReturn(Insumo.Medida.MG);

        Insumo insumo = mock(Insumo.class);
        when(insumoGateway.save(any(Insumo.class))).thenReturn(insumo);

        Insumo resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(insumoGateway, times(1)).save(any(Insumo.class));
    }
}