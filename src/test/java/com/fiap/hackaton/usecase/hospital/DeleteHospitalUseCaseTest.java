package com.fiap.hackaton.usecase.hospital;

import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteHospitalUseCaseTest {

    private HospitalGateway hospitalGateway;
    private DeleteHospitalUseCase useCase;

    @BeforeEach
    void setUp() {
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new DeleteHospitalUseCase(hospitalGateway);
    }

    @Test
    @DisplayName("Deve deletar hospital existente e retornar entidade hospital")
    void shouldDeleteExistingHospitalAndReturnHospitalEntity() throws HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        Hospital hospital = mock(Hospital.class);

        when(hospitalGateway.findById(id)).thenReturn(Optional.of(hospitalSchema));
        when(hospitalSchema.toHospital()).thenReturn(hospital);

        useCase.execute(id);

        verify(hospitalGateway, times(1)).findById(id);
        verify(hospitalGateway, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID id = UUID.randomUUID();
        when(hospitalGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(id));
        verify(hospitalGateway, times(1)).findById(id);
        verify(hospitalGateway, never()).deleteById(id);
    }
}