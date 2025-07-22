package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
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
import com.fiap.hackaton.usecase.estoqueInsumo.dto.IEstoqueInsumoUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateEstoqueInsumoUseCaseTest {

    private EstoqueInsumoGateway estoqueGateway;
    private InsumoGateway insumoGateway;
    private HospitalGateway hospitalGateway;
    private UpdateEstoqueInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        estoqueGateway = mock(EstoqueInsumoGateway.class);
        insumoGateway = mock(InsumoGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new UpdateEstoqueInsumoUseCase(estoqueGateway, insumoGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve atualizar estoque de insumo com dados válidos")
    void shouldUpdateEstoqueInsumoWithValidData() throws EstoqueInsumoNotFoundException, HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        IEstoqueInsumoUpdateData dados = mock(IEstoqueInsumoUpdateData.class);
        EstoqueInsumo estoque = mock(EstoqueInsumo.class);
        InsumoSchema insumoSchema = mock(InsumoSchema.class);
        Insumo insumo = mock(Insumo.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        EstoqueInsumoSchema estoqueInsumoSchema = mock(EstoqueInsumoSchema.class);
        Hospital hospital = mock(Hospital.class);

        List<UUID> itensIds = List.of(UUID.randomUUID());
        List<Long> quantidades = List.of(10L);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoqueInsumoSchema));
        when(estoqueInsumoSchema.toEstoqueInsumo()).thenReturn(estoque);

        when(insumoGateway.findAll()).thenReturn(List.of(insumoSchema));
        when(insumoSchema.getId()).thenReturn(itensIds.get(0));
        when(insumoSchema.toInsumo()).thenReturn(insumo);

        when(dados.itens()).thenReturn(itensIds);
        when(dados.quantidades()).thenReturn(quantidades);
        when(dados.hospital()).thenReturn(UUID.randomUUID());

        when(hospitalGateway.findById(any())).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        when(estoqueGateway.update(any(), any(), any())).thenReturn(estoqueInsumoSchema);
        when(estoqueInsumoSchema.toEstoqueInsumo()).thenReturn(estoque);

        EstoqueInsumo resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(estoqueGateway, times(1)).findById(id);
        verify(insumoGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).findById(any());
        verify(estoqueGateway, times(1)).update(any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar EstoqueInsumoNotFoundException quando estoque não existe")
    void shouldThrowEstoqueInsumoNotFoundExceptionWhenEstoqueDoesNotExist() {
        UUID id = UUID.randomUUID();
        IEstoqueInsumoUpdateData dados = mock(IEstoqueInsumoUpdateData.class);

        when(estoqueGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EstoqueInsumoNotFoundException.class, () -> useCase.execute(id, dados));
        verify(estoqueGateway, times(1)).findById(id);
        verify(insumoGateway, never()).findAll();
        verify(hospitalGateway, never()).findById(any());
        verify(estoqueGateway, never()).update(any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID id = UUID.randomUUID();
        IEstoqueInsumoUpdateData dados = mock(IEstoqueInsumoUpdateData.class);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(mock(com.fiap.hackaton.infrastructure.config.db.schema.EstoqueInsumoSchema.class)));
        when(estoqueGateway.findById(id).get().toEstoqueInsumo()).thenReturn(mock(EstoqueInsumo.class));
        when(insumoGateway.findAll()).thenReturn(Collections.emptyList());
        when(dados.itens()).thenReturn(Collections.emptyList());
        when(dados.hospital()).thenReturn(UUID.randomUUID());
        when(hospitalGateway.findById(any())).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(id, dados));
        verify(hospitalGateway, times(1)).findById(any());
        verify(estoqueGateway, never()).update(any(), any(), any());
    }

    @Test
    @DisplayName("Deve ignorar atualização de itens e quantidades quando listas estão vazias ou nulas")
    void shouldIgnoreUpdateOfItensAndQuantidadesWhenListsAreEmptyOrNull() throws EstoqueInsumoNotFoundException, HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        IEstoqueInsumoUpdateData dados = mock(IEstoqueInsumoUpdateData.class);

        InsumoSchema insumoSchema = mock(InsumoSchema.class);
        Insumo insumo = mock(Insumo.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);

        Hospital hospital = mock(Hospital.class);

        EstoqueInsumoSchema estoqueInsumoSchema1 = mock(EstoqueInsumoSchema.class);
        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoqueInsumoSchema1));
        EstoqueInsumo estoque1 = mock(EstoqueInsumo.class);
        when(estoqueInsumoSchema1.toEstoqueInsumo()).thenReturn(estoque1);

        when(insumoGateway.findAll()).thenReturn(Collections.emptyList());
        when(dados.itens()).thenReturn(Collections.emptyList());
        when(dados.quantidades()).thenReturn(null);
        when(dados.hospital()).thenReturn(UUID.randomUUID());

        when(hospitalGateway.findById(any())).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        EstoqueInsumoSchema estoqueInsumoSchema2 = mock(EstoqueInsumoSchema.class);
        when(estoqueGateway.update(any(), any(), any())).thenReturn(estoqueInsumoSchema2);
        EstoqueInsumo estoque2 = mock(EstoqueInsumo.class);
        when(estoqueInsumoSchema2.toEstoqueInsumo()).thenReturn(estoque2);

        EstoqueInsumo resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(estoqueGateway, times(1)).findById(id);
        verify(insumoGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).findById(any());
        verify(estoqueGateway, times(1)).update(any(), any(), any());
    }

    @Test
    @DisplayName("Deve atualizar apenas o hospital quando itens e quantidades não são fornecidos")
    void shouldUpdateOnlyHospitalWhenItensAndQuantidadesNotProvided() throws EstoqueInsumoNotFoundException, HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        IEstoqueInsumoUpdateData dados = mock(IEstoqueInsumoUpdateData.class);
        EstoqueInsumo estoque = mock(EstoqueInsumo.class);
        EstoqueInsumoSchema estoqueInsumoSchema = mock(EstoqueInsumoSchema.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        Hospital hospital = mock(Hospital.class);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoqueInsumoSchema));
        when(estoqueInsumoSchema.toEstoqueInsumo()).thenReturn(estoque);

        when(insumoGateway.findAll()).thenReturn(Collections.emptyList());
        when(dados.itens()).thenReturn(Collections.emptyList());
        when(dados.quantidades()).thenReturn(Collections.emptyList());
        when(dados.hospital()).thenReturn(UUID.randomUUID());
        when(hospitalGateway.findById(any())).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(hospital);
        when(estoqueGateway.update(any(), any(), any())).thenReturn(estoqueInsumoSchema);
        when(estoqueInsumoSchema.toEstoqueInsumo()).thenReturn(estoque);

        EstoqueInsumo resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(estoqueGateway, times(1)).findById(id);
        verify(insumoGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).findById(any());
        verify(estoqueGateway, times(1)).update(any(), any(), any());
    }
}