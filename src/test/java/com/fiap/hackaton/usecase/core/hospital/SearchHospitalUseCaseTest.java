package com.fiap.hackaton.usecase.core.hospital;

import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchHospitalUseCaseTest {

    private HospitalGateway hospitalGateway;
    private SearchHospitalUseCase useCase;

    @BeforeEach
    void setUp() {
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new SearchHospitalUseCase(hospitalGateway);
    }

    @Test
    @DisplayName("Deve retornar lista de hospitais quando existirem registros")
    void shouldReturnListOfHospitalsWhenRecordsExist() {
        Hospital hospital = mock(Hospital.class);
        when(hospitalGateway.findAll()).thenReturn(List.of(hospital));

        List<Hospital> resultado = useCase.execute();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(hospital, resultado.get(0));
        verify(hospitalGateway, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não existirem registros de hospitais")
    void shouldReturnEmptyListWhenNoHospitalsExist() {
        when(hospitalGateway.findAll()).thenReturn(Collections.emptyList());

        List<Hospital> resultado = useCase.execute();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(hospitalGateway, times(1)).findAll();
    }
}