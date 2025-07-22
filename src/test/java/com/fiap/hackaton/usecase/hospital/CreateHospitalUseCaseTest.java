package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateHospitalUseCaseTest {

    private HospitalGateway hospitalGateway;
    private ColaboradorGateway colaboradorGateway;
    private CreateHospitalUseCase useCase;

    @BeforeEach
    void setUp() {
        hospitalGateway = mock(HospitalGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        useCase = new CreateHospitalUseCase(hospitalGateway, colaboradorGateway);
    }

    @Test
    @DisplayName("Deve criar hospital com dados válidos e colaboradores existentes")
    void shouldCreateHospitalWithValidDataAndExistingColaboradores() {
        UUID colaboradorId = UUID.randomUUID();
        IHospitalRegistrationData dados = mock(IHospitalRegistrationData.class);
        when(dados.colaboradores()).thenReturn(List.of(colaboradorId));
        when(dados.cep()).thenReturn("12345678");
        when(dados.numero()).thenReturn(100);
        when(dados.quantidadeLeitoAtual()).thenReturn(10);
        when(dados.quantidadeLeitoMaximo()).thenReturn(20);

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findAll()).thenReturn(List.of(colaboradorSchema));
        when(colaboradorSchema.getId()).thenReturn(colaboradorId);
        when(colaboradorSchema.toColaborador()).thenReturn(mock(Colaborador.class));

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        Hospital hospital = mock(Hospital.class);
        when(hospitalGateway.save(any(), any())).thenReturn(hospitalSchema);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        Hospital resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(colaboradorGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).save(any(), any());
    }

    @Test
    @DisplayName("Deve criar hospital sem colaboradores quando lista de colaboradores está vazia")
    void shouldCreateHospitalWithNoColaboradoresWhenColaboradoresListIsEmpty() {
        IHospitalRegistrationData dados = mock(IHospitalRegistrationData.class);
        when(dados.colaboradores()).thenReturn(Collections.emptyList());
        when(dados.cep()).thenReturn("87654321");
        when(dados.numero()).thenReturn(200);
        when(dados.quantidadeLeitoAtual()).thenReturn(5);
        when(dados.quantidadeLeitoMaximo()).thenReturn(10);

        when(colaboradorGateway.findAll()).thenReturn(Collections.emptyList());

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        Hospital hospital = mock(Hospital.class);
        when(hospitalGateway.save(any(), any())).thenReturn(hospitalSchema);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        Hospital resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(colaboradorGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).save(any(), any());
    }

    @Test
    @DisplayName("Deve ignorar colaboradores inexistentes na lista de colaboradores")
    void shouldIgnoreNonexistentColaboradoresInColaboradoresList() {
        UUID colaboradorId = UUID.randomUUID();
        IHospitalRegistrationData dados = mock(IHospitalRegistrationData.class);
        when(dados.colaboradores()).thenReturn(List.of(colaboradorId));
        when(dados.cep()).thenReturn("11111111");
        when(dados.numero()).thenReturn(300);
        when(dados.quantidadeLeitoAtual()).thenReturn(15);
        when(dados.quantidadeLeitoMaximo()).thenReturn(30);

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findAll()).thenReturn(Collections.emptyList());

        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        Hospital hospital = mock(Hospital.class);
        when(hospitalGateway.save(any(), any())).thenReturn(hospitalSchema);
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        Hospital resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(colaboradorGateway, times(1)).findAll();
        verify(hospitalGateway, times(1)).save(any(), any());
    }
}