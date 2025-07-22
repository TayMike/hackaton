package com.fiap.hackaton.usecase.estoqueEquipamento;

import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueEquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.estoqueEquipamento.dto.IEstoqueEquipamentoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateEstoqueEquipamentoUseCaseTest {

    private EstoqueEquipamentoGateway estoqueGateway;
    private EquipamentoGateway equipamentoGateway;
    private HospitalGateway hospitalGateway;
    private CreateEstoqueEquipamentoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueEquipamentoGateway.class);
        equipamentoGateway = mock(EquipamentoGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateEstoqueEquipamentoUseCase(estoqueGateway, equipamentoGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar estoque de equipamentos com dados válidos")
    void shouldCreateEstoqueEquipamentoWithValidData() throws HospitalNotFoundException {
        IEstoqueEquipamentoRegistrationData dados = mock(IEstoqueEquipamentoRegistrationData.class);
        UUID hospitalId = UUID.randomUUID();
        UUID equipamentoId = UUID.randomUUID();

        EquipamentoSchema equipamentoSchema = mock(EquipamentoSchema.class);
        Equipamento equipamento = mock(Equipamento.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        EstoqueEquipamento estoqueEquipamento = mock(EstoqueEquipamento.class);
        EstoqueEquipamentoSchema estoqueEquipamentoSchema = mock(EstoqueEquipamentoSchema.class);

        when(dados.itens()).thenReturn(List.of(equipamentoId));
        when(dados.quantidades()).thenReturn(List.of(10L));
        when(dados.hospital()).thenReturn(hospitalId);

        when(equipamentoGateway.findAll()).thenReturn(List.of(equipamentoSchema));
        when(equipamentoSchema.getId()).thenReturn(equipamentoId);
        when(equipamentoSchema.toEquipamento()).thenReturn(equipamento);

        when(hospitalGateway.findById(hospitalId)).thenReturn(java.util.Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(mock(com.fiap.hackaton.entity.hospital.model.Hospital.class));

        when(estoqueGateway.save(any(), eq(List.of(equipamentoSchema)), eq(hospitalSchema))).thenReturn(estoqueEquipamentoSchema);
        when(estoqueEquipamentoSchema.toEstoqueEquipamento()).thenReturn(estoqueEquipamento);

        EstoqueEquipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(estoqueGateway, times(1)).save(any(), eq(List.of(equipamentoSchema)), eq(hospitalSchema));
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        IEstoqueEquipamentoRegistrationData dados = mock(IEstoqueEquipamentoRegistrationData.class);
        UUID hospitalId = UUID.randomUUID();
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.itens()).thenReturn(Collections.emptyList());
        when(dados.quantidades()).thenReturn(Collections.emptyList());
        when(hospitalGateway.findById(hospitalId)).thenReturn(java.util.Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(estoqueGateway, never()).save(any(), any(), any());
    }

    @Test
    @DisplayName("Deve criar estoque de equipamentos vazio quando não há itens")
    void shouldCreateEmptyEstoqueEquipamentoWhenNoItemsProvided() throws HospitalNotFoundException {
        IEstoqueEquipamentoRegistrationData dados = mock(IEstoqueEquipamentoRegistrationData.class);
        UUID hospitalId = UUID.randomUUID();

        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.itens()).thenReturn(Collections.emptyList());
        when(dados.quantidades()).thenReturn(Collections.emptyList());
        when(equipamentoGateway.findAll()).thenReturn(Collections.emptyList());

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(java.util.Optional.of(hospitalSchema));

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        EstoqueEquipamentoSchema estoqueEquipamentoSchema = mock(EstoqueEquipamentoSchema.class);
        when(estoqueGateway.save(any(), eq(Collections.emptyList()), eq(hospitalSchema))).thenReturn(estoqueEquipamentoSchema);

        EstoqueEquipamento estoqueEquipamento = mock(EstoqueEquipamento.class);
        when(estoqueEquipamentoSchema.toEstoqueEquipamento()).thenReturn(estoqueEquipamento);

        EstoqueEquipamento resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(equipamentoGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(estoqueGateway, times(1)).save(any(), eq(Collections.emptyList()), eq(hospitalSchema));
    }
}