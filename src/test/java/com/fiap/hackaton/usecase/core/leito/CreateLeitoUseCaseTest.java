package com.fiap.hackaton.usecase.core.leito;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.leito.model.Leito;
import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.usecase.core.leito.dto.ILeitoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateLeitoUseCaseTest {

    private LeitoGateway leitoGateway;
    private HospitalGateway hospitalGateway;
    private PacienteGateway pacienteGateway;
    private CreateLeitoUseCase useCase;

    @BeforeEach
    void setUp() {
        leitoGateway = mock(LeitoGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        pacienteGateway = mock(PacienteGateway.class);
        useCase = new CreateLeitoUseCase(leitoGateway, hospitalGateway, pacienteGateway);
    }

    @Test
    @DisplayName("Deve criar leito com dados válidos")
    void shouldCreateLeitoWithValidData() throws HospitalNotFoundException, PacienteNotFoundException {
        ILeitoRegistrationData dados = mock(ILeitoRegistrationData.class);
        Hospital hospital = mock(Hospital.class);
        Paciente paciente = mock(Paciente.class);
        Leito leito = mock(Leito.class);

        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.pacienteId()).thenReturn(pacienteId);
        when(dados.identificacao()).thenReturn("A101");
        when(dados.pavilhao()).thenReturn("Pavilhão 1");
        when(dados.quarto()).thenReturn("Quarto 10");

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));
        when(pacienteGateway.findById(pacienteId)).thenReturn(Optional.of(paciente));
        when(leitoGateway.save(any(Leito.class))).thenReturn(leito);

        Leito resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verify(leitoGateway, times(1)).save(any(Leito.class));
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        ILeitoRegistrationData dados = mock(ILeitoRegistrationData.class);
        UUID hospitalId = UUID.randomUUID();

        when(dados.hospitalId()).thenReturn(hospitalId);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(pacienteGateway, never()).findById(any());
        verify(leitoGateway, never()).save(any());
    }

    @Test
    @DisplayName("Deve lançar PacienteNotFoundException quando paciente não existe")
    void shouldThrowPacienteNotFoundExceptionWhenPacienteDoesNotExist() {
        ILeitoRegistrationData dados = mock(ILeitoRegistrationData.class);
        Hospital hospital = mock(Hospital.class);
        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.pacienteId()).thenReturn(pacienteId);

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));
        when(pacienteGateway.findById(pacienteId)).thenReturn(Optional.empty());

        assertThrows(PacienteNotFoundException.class, () -> useCase.execute(dados));
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verify(leitoGateway, never()).save(any());
    }

    @Test
    @DisplayName("Deve criar leito mesmo com campos de identificação vazios")
    void shouldCreateLeitoWithEmptyIdentificationFields() throws HospitalNotFoundException, PacienteNotFoundException {
        ILeitoRegistrationData dados = mock(ILeitoRegistrationData.class);
        Hospital hospital = mock(Hospital.class);
        Paciente paciente = mock(Paciente.class);
        Leito leito = mock(Leito.class);

        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.pacienteId()).thenReturn(pacienteId);
        when(dados.identificacao()).thenReturn("");
        when(dados.pavilhao()).thenReturn("");
        when(dados.quarto()).thenReturn("");

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));
        when(pacienteGateway.findById(pacienteId)).thenReturn(Optional.of(paciente));
        when(leitoGateway.save(any(Leito.class))).thenReturn(leito);

        Leito resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(leitoGateway, times(1)).save(any(Leito.class));
    }
}