package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateHospitalUseCaseTest {

    private HospitalGateway hospitalGateway;
    private ColaboradorGateway colaboradorGateway;
    private UpdateHospitalUseCase useCase;

    @BeforeEach
    void setUp() {
        hospitalGateway = mock(HospitalGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        useCase = new UpdateHospitalUseCase(hospitalGateway, colaboradorGateway);
    }

    @Test
    @DisplayName("Deve atualizar hospital com todos os campos e colaboradores válidos")
    void shouldUpdateHospitalWithAllFieldsAndValidColaboradores() throws HospitalNotFoundException {
        UUID hospitalId = UUID.randomUUID();
        IHospitalUpdateData dados = mock(IHospitalUpdateData.class);
        UUID colaboradorId = UUID.randomUUID();

        when(dados.colaboradores()).thenReturn(List.of(colaboradorId));
        when(dados.cep()).thenReturn("12345678");
        when(dados.numero()).thenReturn(100);
        when(dados.quantidadeLeitoAtual()).thenReturn(10);
        when(dados.quantidadeLeitoMaximo()).thenReturn(20);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        Hospital hospital = mock(Hospital.class);
        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        Colaborador colaborador = mock(Colaborador.class);

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(hospital);
        when(colaboradorGateway.findAll()).thenReturn(List.of(colaboradorSchema));
        when(colaboradorSchema.getId()).thenReturn(colaboradorId);
        when(colaboradorSchema.toColaborador()).thenReturn(colaborador);
        when(hospitalGateway.update(any(), any())).thenReturn(hospitalSchema);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        Hospital resultado = useCase.execute(hospitalId, dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(colaboradorGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).update(any(), any());
    }

    @Test
    @DisplayName("Deve atualizar hospital ignorando campos nulos ou em branco")
    void shouldUpdateHospitalIgnoringNullOrBlankFields() throws HospitalNotFoundException {
        UUID hospitalId = UUID.randomUUID();
        IHospitalUpdateData dados = mock(IHospitalUpdateData.class);

        when(dados.colaboradores()).thenReturn(Collections.emptyList());
        when(dados.cep()).thenReturn("");
        when(dados.numero()).thenReturn(null);
        when(dados.quantidadeLeitoAtual()).thenReturn(null);
        when(dados.quantidadeLeitoMaximo()).thenReturn(null);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        Hospital hospital = mock(Hospital.class);

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(hospital);
        when(colaboradorGateway.findAll()).thenReturn(Collections.emptyList());
        when(hospitalGateway.update(any(), any())).thenReturn(hospitalSchema);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        Hospital resultado = useCase.execute(hospitalId, dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(colaboradorGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).update(any(), any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID hospitalId = UUID.randomUUID();
        IHospitalUpdateData dados = mock(IHospitalUpdateData.class);

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(hospitalId, dados));
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(colaboradorGateway, never()).findAll();
        verify(hospitalGateway, never()).update(any(), any());
    }

    @Test
    @DisplayName("Deve atualizar hospital sem colaboradores quando lista de colaboradores está vazia")
    void shouldUpdateHospitalWithNoColaboradoresWhenColaboradoresListIsEmpty() throws HospitalNotFoundException {
        UUID hospitalId = UUID.randomUUID();
        IHospitalUpdateData dados = mock(IHospitalUpdateData.class);

        when(dados.colaboradores()).thenReturn(Collections.emptyList());
        when(dados.cep()).thenReturn("87654321");
        when(dados.numero()).thenReturn(200);
        when(dados.quantidadeLeitoAtual()).thenReturn(5);
        when(dados.quantidadeLeitoMaximo()).thenReturn(10);

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        Hospital hospital = mock(Hospital.class);

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(hospital);
        when(colaboradorGateway.findAll()).thenReturn(Collections.emptyList());
        when(hospitalGateway.update(any(), any())).thenReturn(hospitalSchema);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        Hospital resultado = useCase.execute(hospitalId, dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(colaboradorGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).update(any(), any());
    }
}