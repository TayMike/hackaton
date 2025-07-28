package com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento;

import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.dto.IEstoqueEquipamentoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.UUID;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateEstoqueEquipamentoUseCaseTest {

    private EstoqueEquipamentoGateway estoqueGateway;
    private EquipamentoGateway equipamentoGateway;
    private ColaboradorGateway colaboradorGateway;
    private HospitalGateway hospitalGateway;
    private CreateEstoqueEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueEquipamentoGateway.class);
        equipamentoGateway = mock(EquipamentoGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateEstoqueEquipamentoUseCase(estoqueGateway, equipamentoGateway, colaboradorGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar estoque de equipamento com dados válidos")
    void shouldCreateEstoqueEquipamentoWithValidData() throws HospitalNotFoundException, EquipamentoNotFoundException {
        IEstoqueEquipamentoRegistrationData dados = mock(IEstoqueEquipamentoRegistrationData.class);

        UUID equipamentoId = UUID.randomUUID();
        UUID colaboradorEntregadorId = UUID.randomUUID();
        UUID colaboradorResponsavelId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();
        OffsetDateTime dataHoraColeta = OffsetDateTime.now();

        Equipamento equipamento = mock(Equipamento.class);
        Colaborador colaboradorEntregador = mock(Colaborador.class);
        Colaborador colaboradorResponsavel = mock(Colaborador.class);
        Hospital hospital = mock(Hospital.class);
        EstoqueEquipamento estoqueEquipamentoSalvo = mock(EstoqueEquipamento.class);

        when(dados.equipamentoId()).thenReturn(equipamentoId);
        when(dados.colaboradorEntregadorId()).thenReturn(colaboradorEntregadorId);
        when(dados.dataHoraColeta()).thenReturn(dataHoraColeta);
        when(dados.colaboradorResponsavelId()).thenReturn(colaboradorResponsavelId);
        when(dados.hospitalId()).thenReturn(hospitalId);

        when(equipamentoGateway.findById(equipamentoId)).thenReturn(Optional.of(equipamento));
        when(colaboradorGateway.findById(colaboradorEntregadorId)).thenReturn(Optional.of(colaboradorEntregador));
        when(colaboradorGateway.findById(colaboradorResponsavelId)).thenReturn(Optional.of(colaboradorResponsavel));
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));

        when(estoqueGateway.save(any(EstoqueEquipamento.class))).thenReturn(estoqueEquipamentoSalvo);

        EstoqueEquipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);

        verify(equipamentoGateway, times(1)).findById(equipamentoId);
        verify(colaboradorGateway, times(1)).findById(colaboradorEntregadorId);
        verify(colaboradorGateway, times(1)).findById(colaboradorResponsavelId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(estoqueGateway, times(1)).save(any(EstoqueEquipamento.class));
    }

    @Test
    @DisplayName("Deve lançar EquipamentoNotFoundException quando equipamento não existe")
    void shouldThrowEquipamentoNotFoundExceptionWhenEquipamentoDoesNotExist() {
        IEstoqueEquipamentoRegistrationData dados = mock(IEstoqueEquipamentoRegistrationData.class);
        UUID equipamentoId = UUID.randomUUID();

        when(dados.equipamentoId()).thenReturn(equipamentoId);
        when(equipamentoGateway.findById(equipamentoId)).thenReturn(Optional.empty());

        assertThrows(EquipamentoNotFoundException.class, () -> useCase.execute(dados));

        verify(equipamentoGateway, times(1)).findById(equipamentoId);
        verify(colaboradorGateway, never()).findById(any());
        verify(hospitalGateway, never()).findById(any());
        verify(estoqueGateway, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        IEstoqueEquipamentoRegistrationData dados = mock(IEstoqueEquipamentoRegistrationData.class);
        UUID equipamentoId = UUID.randomUUID();
        UUID colaboradorEntregadorId = UUID.randomUUID();
        UUID colaboradorResponsavelId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        when(dados.equipamentoId()).thenReturn(equipamentoId);
        when(dados.colaboradorEntregadorId()).thenReturn(colaboradorEntregadorId);
        when(dados.colaboradorResponsavelId()).thenReturn(colaboradorResponsavelId);
        when(dados.hospitalId()).thenReturn(hospitalId);

        when(equipamentoGateway.findById(equipamentoId)).thenReturn(Optional.of(mock(Equipamento.class)));
        when(colaboradorGateway.findById(colaboradorEntregadorId)).thenReturn(Optional.of(mock(Colaborador.class)));
        when(colaboradorGateway.findById(colaboradorResponsavelId)).thenReturn(Optional.of(mock(Colaborador.class)));
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));

        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(estoqueGateway, never()).save(any());
    }
}