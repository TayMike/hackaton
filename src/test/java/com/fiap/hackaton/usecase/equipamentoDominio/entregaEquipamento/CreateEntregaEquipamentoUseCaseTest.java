package com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.exception.EntregaEquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento.dto.IEntregaEquipamentoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateEntregaEquipamentoUseCaseTest {

    private EntregaEquipamentoGateway entregaEquipamentoGateway;
    private EquipamentoGateway equipamentoGateway;
    private ColaboradorGateway colaboradorGateway;
    private HospitalGateway hospitalGateway;
    private CreateEntregaEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        entregaEquipamentoGateway = mock(EntregaEquipamentoGateway.class);
        equipamentoGateway = mock(EquipamentoGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateEntregaEquipamentoUseCase(entregaEquipamentoGateway, equipamentoGateway, colaboradorGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar entrega de equipamento com dados válidos")
    void shouldCreateEntregaEquipamentoWithValidData() throws ColaboradorNotFoundException, HospitalNotFoundException, EntregaEquipamentoNotFoundException {
        UUID equipamentoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();
        OffsetDateTime dataHora = OffsetDateTime.now();

        IEntregaEquipamentoRegistrationData dados = mock(IEntregaEquipamentoRegistrationData.class);
        when(dados.equipamentoId()).thenReturn(equipamentoId);
        when(dados.colaboradorRecebedorId()).thenReturn(colaboradorId);
        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.dataHoraRecebimento()).thenReturn(dataHora);

        Equipamento equipamento = mock(Equipamento.class);
        Colaborador colaborador = mock(Colaborador.class);
        Hospital hospital = mock(Hospital.class);

        when(equipamentoGateway.findById(equipamentoId)).thenReturn(Optional.of(equipamento));
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaborador));
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));

        EntregaEquipamento entregaMock = mock(EntregaEquipamento.class);
        when(entregaEquipamentoGateway.save(any())).thenReturn(entregaMock);

        EntregaEquipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(equipamentoGateway).findById(equipamentoId);
        verify(colaboradorGateway).findById(colaboradorId);
        verify(hospitalGateway).findById(hospitalId);
        verify(entregaEquipamentoGateway).save(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando equipamento não existir")
    void shouldThrowWhenEquipamentoNotFound() {
        UUID equipamentoId = UUID.randomUUID();

        IEntregaEquipamentoRegistrationData dados = mock(IEntregaEquipamentoRegistrationData.class);
        when(dados.equipamentoId()).thenReturn(equipamentoId);

        when(equipamentoGateway.findById(equipamentoId)).thenReturn(Optional.empty());

        assertThrows(EntregaEquipamentoNotFoundException.class, () -> useCase.execute(dados));
        verify(equipamentoGateway).findById(equipamentoId);
    }

    @Test
    @DisplayName("Deve lançar exceção quando colaborador não existir")
    void shouldThrowWhenColaboradorNotFound() {
        UUID equipamentoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();

        IEntregaEquipamentoRegistrationData dados = mock(IEntregaEquipamentoRegistrationData.class);
        when(dados.equipamentoId()).thenReturn(equipamentoId);
        when(dados.colaboradorRecebedorId()).thenReturn(colaboradorId);

        when(equipamentoGateway.findById(equipamentoId)).thenReturn(Optional.of(mock(Equipamento.class)));
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway).findById(colaboradorId);
    }

    @Test
    @DisplayName("Deve lançar exceção quando hospital não existir")
    void shouldThrowWhenHospitalNotFound() {
        UUID equipamentoId = UUID.randomUUID();
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IEntregaEquipamentoRegistrationData dados = mock(IEntregaEquipamentoRegistrationData.class);
        when(dados.equipamentoId()).thenReturn(equipamentoId);
        when(dados.colaboradorRecebedorId()).thenReturn(colaboradorId);
        when(dados.hospitalId()).thenReturn(hospitalId);

        when(equipamentoGateway.findById(equipamentoId)).thenReturn(Optional.of(mock(Equipamento.class)));
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(mock(Colaborador.class)));
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(hospitalGateway).findById(hospitalId);
    }
}