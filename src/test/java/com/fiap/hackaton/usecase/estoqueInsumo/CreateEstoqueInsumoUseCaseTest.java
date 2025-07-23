package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueInsumoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;
import com.fiap.hackaton.usecase.estoqueInsumo.dto.IEstoqueInsumoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateEstoqueInsumoUseCaseTest {

    private EstoqueInsumoGateway estoqueGateway;
    private InsumoGateway insumoGateway;
    private HospitalGateway hospitalGateway;
    private CreateEstoqueInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueInsumoGateway.class);
        insumoGateway = mock(InsumoGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateEstoqueInsumoUseCase(estoqueGateway, insumoGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar estoque de insumos com dados válidos")
    void shouldCreateEstoqueInsumoWithValidData() throws HospitalNotFoundException {
        IEstoqueInsumoRegistrationData dados = mock(IEstoqueInsumoRegistrationData.class);
        UUID hospitalId = UUID.randomUUID();
        UUID insumoId = UUID.randomUUID();

        InsumoSchema insumoSchema = mock(InsumoSchema.class);
        Insumo insumo = mock(Insumo.class);

        when(dados.itens()).thenReturn(List.of(insumoId));
        when(dados.quantidades()).thenReturn(List.of(10L));
        when(dados.hospital()).thenReturn(hospitalId);

        when(insumoGateway.findAll()).thenReturn(List.of(insumoSchema));
        when(insumoSchema.getId()).thenReturn(insumoId);
        when(insumoSchema.toInsumo()).thenReturn(insumo);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(java.util.Optional.of(hospitalSchema));

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        EstoqueInsumoSchema estoqueInsumoSchema = mock(EstoqueInsumoSchema.class);
        when(estoqueGateway.save(any(), any(), eq(hospitalSchema))).thenReturn(estoqueInsumoSchema);

        EstoqueInsumo estoqueInsumo = mock(EstoqueInsumo.class);
        when(estoqueInsumoSchema.toEstoqueInsumo()).thenReturn(estoqueInsumo);

        EstoqueInsumo resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(insumoGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(estoqueGateway, times(1)).save(any(), eq(List.of(insumoSchema)), eq(hospitalSchema));
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        IEstoqueInsumoRegistrationData dados = mock(IEstoqueInsumoRegistrationData.class);
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
    @DisplayName("Deve criar estoque de insumos vazio quando não há itens")
    void shouldCreateEmptyEstoqueInsumoWhenNoItemsProvided() throws HospitalNotFoundException {
        IEstoqueInsumoRegistrationData dados = mock(IEstoqueInsumoRegistrationData.class);
        UUID hospitalId = UUID.randomUUID();


        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.itens()).thenReturn(Collections.emptyList());
        when(dados.quantidades()).thenReturn(Collections.emptyList());
        when(insumoGateway.findAll()).thenReturn(Collections.emptyList());

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(java.util.Optional.of(hospitalSchema));

        Hospital hospital = mock(Hospital.class);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        EstoqueInsumoSchema estoqueInsumoSchema = mock(EstoqueInsumoSchema.class);
        when(estoqueGateway.save(any(), eq(Collections.emptyList()), eq(hospitalSchema))).thenReturn(estoqueInsumoSchema);

        EstoqueInsumo estoqueInsumo = mock(EstoqueInsumo.class);
        when(estoqueInsumoSchema.toEstoqueInsumo()).thenReturn(estoqueInsumo);

        EstoqueInsumo resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(insumoGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(estoqueGateway, times(1)).save(any(), eq(Collections.emptyList()), eq(hospitalSchema));
    }
}