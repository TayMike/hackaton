package com.fiap.hackaton.usecase.core.hospital;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetHospitalUseCaseTest {

    private HospitalGateway hospitalGateway;
    private GetHospitalUseCase useCase;

    @BeforeEach
    void setUp() {
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new GetHospitalUseCase(hospitalGateway);
    }

    @Test
    @DisplayName("Deve retornar hospital quando encontrado pelo id")
    void shouldReturnHospitalWhenFoundById() throws HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        Hospital hospital = mock(Hospital.class);

        when(hospitalGateway.findById(id)).thenReturn(Optional.of(hospital));

        Hospital resultado = useCase.execute(id);

        assertNotNull(resultado);
        assertEquals(hospital, resultado);
        verify(hospitalGateway, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(hospitalGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(id));
        verify(hospitalGateway, times(1)).findById(id);
    }
}