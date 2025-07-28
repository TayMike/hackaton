package com.fiap.hackaton.usecase.insumoDominio.coletaInsumo;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.usecase.insumoDominio.coletaInsumo.dto.IColetaInsumoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateColetaInsumoUseCaseTest {

    private ColetaInsumoGateway coletaInsumoGateway;
    private ColaboradorGateway colaboradorGateway;
    private PacienteGateway pacienteGateway;
    private HospitalGateway hospitalGateway;
    private EstoqueInsumoGateway estoqueInsumoGateway;
    private CreateColetaInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        coletaInsumoGateway = mock(ColetaInsumoGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        pacienteGateway = mock(PacienteGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        estoqueInsumoGateway = mock(EstoqueInsumoGateway.class);
        useCase = new CreateColetaInsumoUseCase(coletaInsumoGateway, colaboradorGateway, pacienteGateway, hospitalGateway, estoqueInsumoGateway);
    }

    @Test
    @DisplayName("Deve criar coleta de insumo com dados válidos e lista de QuantidadeInsumo")
    void shouldCreateColetaInsumoWithValidDataAndQuantidadeInsumoList() throws Exception {
        UUID colaboradorId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();
        List<QuantidadeInsumo> insumos = List.of(
                new QuantidadeInsumo(UUID.randomUUID(), 5L),
                new QuantidadeInsumo(UUID.randomUUID(), 10L)
        );
        OffsetDateTime dataHora = OffsetDateTime.now();

        IColetaInsumoRegistrationData dados = mock(IColetaInsumoRegistrationData.class);
        when(dados.colaboradorEntregadorId()).thenReturn(colaboradorId);
        when(dados.pacienteRecebedorId()).thenReturn(pacienteId);
        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.insumos()).thenReturn(insumos);
        when(dados.dataHoraColeta()).thenReturn(dataHora);

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(java.util.Optional.of(mock(Colaborador.class)));
        when(pacienteGateway.findById(pacienteId)).thenReturn(java.util.Optional.of(mock(Paciente.class)));
        when(hospitalGateway.findById(hospitalId)).thenReturn(java.util.Optional.of(mock(Hospital.class)));

        EstoqueInsumo estoqueInsumoMock = mock(EstoqueInsumo.class);
        when(estoqueInsumoGateway.findByHospitalId(hospitalId)).thenReturn(java.util.Optional.of(estoqueInsumoMock));

        doNothing().when(estoqueInsumoMock).diminuirComColeta(any(ColetaInsumo.class));

        when(estoqueInsumoGateway.save(any(EstoqueInsumo.class))).thenReturn(null);
        when(coletaInsumoGateway.save(any(ColetaInsumo.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ColetaInsumo resultado = useCase.execute(dados);

        assertNotNull(resultado);
        assertEquals(insumos, resultado.getInsumos());
        assertEquals(colaboradorId, resultado.getColaboradorEntregadorId());
        assertEquals(pacienteId, resultado.getPacienteRecebedorId());
        assertEquals(hospitalId, resultado.getHospitalId());
        assertEquals(dataHora, resultado.getDataHoraColeta());

        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(estoqueInsumoGateway, times(1)).findByHospitalId(hospitalId);
        verify(estoqueInsumoMock, times(1)).diminuirComColeta(any(ColetaInsumo.class));
        verify(estoqueInsumoGateway, times(1)).save(estoqueInsumoMock);
        verify(coletaInsumoGateway, times(1)).save(any(ColetaInsumo.class));
    }

    @Test
    @DisplayName("Deve lançar ColaboradorNotFoundException quando colaborador não existe")
    void shouldThrowColaboradorNotFoundExceptionWhenColaboradorDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        IColetaInsumoRegistrationData dados = mock(IColetaInsumoRegistrationData.class);
        when(dados.colaboradorEntregadorId()).thenReturn(colaboradorId);

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(java.util.Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verifyNoInteractions(pacienteGateway, hospitalGateway, estoqueInsumoGateway, coletaInsumoGateway);
    }

    @Test
    @DisplayName("Deve lançar PacienteNotFoundException quando paciente não existe")
    void shouldThrowPacienteNotFoundExceptionWhenPacienteDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        IColetaInsumoRegistrationData dados = mock(IColetaInsumoRegistrationData.class);
        when(dados.colaboradorEntregadorId()).thenReturn(colaboradorId);
        when(dados.pacienteRecebedorId()).thenReturn(pacienteId);

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(java.util.Optional.of(mock(Colaborador.class)));
        when(pacienteGateway.findById(pacienteId)).thenReturn(java.util.Optional.empty());

        assertThrows(PacienteNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verifyNoInteractions(hospitalGateway, estoqueInsumoGateway, coletaInsumoGateway);
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();
        IColetaInsumoRegistrationData dados = mock(IColetaInsumoRegistrationData.class);
        when(dados.colaboradorEntregadorId()).thenReturn(colaboradorId);
        when(dados.pacienteRecebedorId()).thenReturn(pacienteId);
        when(dados.hospitalId()).thenReturn(hospitalId);

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(java.util.Optional.of(mock(Colaborador.class)));
        when(pacienteGateway.findById(pacienteId)).thenReturn(java.util.Optional.of(mock(Paciente.class)));
        when(hospitalGateway.findById(hospitalId)).thenReturn(java.util.Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verifyNoInteractions(estoqueInsumoGateway, coletaInsumoGateway);
    }
}