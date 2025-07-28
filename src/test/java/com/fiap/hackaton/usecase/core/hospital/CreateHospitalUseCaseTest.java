package com.fiap.hackaton.usecase.core.hospital;

import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.hospital.dto.IHospitalRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateHospitalUseCaseTest {

    private HospitalGateway hospitalGateway;
    private CreateHospitalUseCase useCase;

    @BeforeEach
    void setUp() {
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new CreateHospitalUseCase(hospitalGateway);
    }

    @Test
    @DisplayName("Deve criar hospital com dados válidos")
    void shouldCreateHospitalWithValidData() {
        IHospitalRegistrationData dados = mock(IHospitalRegistrationData.class);

        when(dados.nome()).thenReturn("Hospital Teste");
        when(dados.cnpj()).thenReturn("12312312312312");
        when(dados.colaboradoresIds()).thenReturn(List.of(UUID.randomUUID()));
        when(dados.cep()).thenReturn("12345678");
        when(dados.numero()).thenReturn(100);
        when(dados.quantidadeLeitoAtual()).thenReturn(10);
        when(dados.quantidadeLeitoMaximo()).thenReturn(20);

        Hospital hospitalMock = mock(Hospital.class);
        when(hospitalGateway.save(any(Hospital.class))).thenReturn(hospitalMock);

        Hospital resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).save(any(Hospital.class));
    }

    @Test
    @DisplayName("Deve criar hospital sem colaboradores quando lista está vazia")
    void shouldCreateHospitalWithEmptyColaboradoresList() {
        IHospitalRegistrationData dados = mock(IHospitalRegistrationData.class);

        when(dados.nome()).thenReturn("Hospital Teste");
        when(dados.cnpj()).thenReturn("12312312312312");
        when(dados.colaboradoresIds()).thenReturn(Collections.emptyList());
        when(dados.cep()).thenReturn("87654321");
        when(dados.numero()).thenReturn(200);
        when(dados.quantidadeLeitoAtual()).thenReturn(5);
        when(dados.quantidadeLeitoMaximo()).thenReturn(10);

        Hospital hospitalMock = mock(Hospital.class);
        when(hospitalGateway.save(any(Hospital.class))).thenReturn(hospitalMock);

        Hospital resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).save(any(Hospital.class));
    }
}