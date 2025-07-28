package com.fiap.hackaton.usecase.insumoDominio.entregaInsumo;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.usecase.insumoDominio.entregaInsumo.dto.IEntregaInsumoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateEntregaInsumoUseCaseTest {

    private EntregaInsumoGateway entregaInsumoGateway;
    private ColaboradorGateway colaboradorGateway;
    private HospitalGateway hospitalGateway;
    private EstoqueInsumoGateway estoqueInsumoGateway;

    private CreateEntregaInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        entregaInsumoGateway = mock(EntregaInsumoGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        estoqueInsumoGateway = mock(EstoqueInsumoGateway.class);

        useCase = new CreateEntregaInsumoUseCase(entregaInsumoGateway, colaboradorGateway, hospitalGateway, estoqueInsumoGateway);
    }

    @Test
    @DisplayName("Deve criar entrega de insumo com dados válidos")
    void shouldCreateEntregaInsumoWithValidData() throws HospitalNotFoundException, ColaboradorNotFoundException {
        UUID insumoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        QuantidadeInsumo quantidadeInsumo = new QuantidadeInsumo(insumoId, 5L);
        OffsetDateTime dataHora = OffsetDateTime.now();

        IEntregaInsumoRegistrationData dados = mock(IEntregaInsumoRegistrationData.class);
        when(dados.insumos()).thenReturn(List.of(quantidadeInsumo));
        when(dados.colaboradorRecebedorId()).thenReturn(colaboradorId);
        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.dataHoraRecebimento()).thenReturn(dataHora);

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaborador));

        Hospital hospital = mock(Hospital.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));

        EstoqueInsumo estoqueInsumoMock = mock(EstoqueInsumo.class);
        when(estoqueInsumoGateway.findByHospitalId(hospitalId)).thenReturn(Optional.of(estoqueInsumoMock));
        doNothing().when(estoqueInsumoMock).aumentarComEntrega(any(EntregaInsumo.class));
        when(estoqueInsumoGateway.save(estoqueInsumoMock)).thenReturn(estoqueInsumoMock);

        EntregaInsumo entregaInsumoMock = mock(EntregaInsumo.class);
        when(entregaInsumoGateway.save(any(EntregaInsumo.class))).thenReturn(entregaInsumoMock);

        EntregaInsumo resultado = useCase.execute(dados);

        assertNotNull(resultado);

        verify(colaboradorGateway).findById(colaboradorId);
        verify(hospitalGateway).findById(hospitalId);
        verify(estoqueInsumoGateway).findByHospitalId(hospitalId);
        verify(estoqueInsumoMock).aumentarComEntrega(any(EntregaInsumo.class));
        verify(estoqueInsumoGateway).save(estoqueInsumoMock);
        verify(entregaInsumoGateway).save(any(EntregaInsumo.class));
    }

    @Test
    @DisplayName("Deve lançar ColaboradorNotFoundException quando colaborador não existe")
    void shouldThrowColaboradorNotFoundExceptionWhenColaboradorDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaInsumoRegistrationData dados = mock(IEntregaInsumoRegistrationData.class);
        when(dados.colaboradorRecebedorId()).thenReturn(colaboradorId);
        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.insumos()).thenReturn(List.of());

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, never()).findById(any());
        verify(entregaInsumoGateway, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaInsumoRegistrationData dados = mock(IEntregaInsumoRegistrationData.class);
        when(dados.colaboradorRecebedorId()).thenReturn(colaboradorId);
        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.insumos()).thenReturn(List.of());

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaborador));
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(entregaInsumoGateway, never()).save(any());
    }
}