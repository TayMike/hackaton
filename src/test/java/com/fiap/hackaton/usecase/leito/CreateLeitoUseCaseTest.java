package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.LeitoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;
import com.fiap.hackaton.usecase.leito.dto.ILeitoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        PacienteSchema pacienteSchema = mock(PacienteSchema.class);
        Leito leito = mock(Leito.class);
        LeitoSchema leitoSchema = mock(LeitoSchema.class);

        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.paciente()).thenReturn(pacienteId);
        when(dados.identificacao()).thenReturn("A101");
        when(dados.pavilhao()).thenReturn("Pavilhão 1");
        when(dados.quarto()).thenReturn("Quarto 10");
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));
        when(pacienteGateway.findById(pacienteId)).thenReturn(Optional.of(pacienteSchema));
        when(hospitalSchema.toHospital()).thenReturn(mock(com.fiap.hackaton.entity.hospital.model.Hospital.class));
        when(pacienteSchema.toPaciente()).thenReturn(mock(com.fiap.hackaton.entity.paciente.model.Paciente.class));
        when(leitoGateway.save(any(Leito.class), eq(hospitalSchema), eq(pacienteSchema))).thenReturn(leitoSchema);
        when(leitoSchema.toLeito()).thenReturn(leito);

        Leito resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(leitoGateway, times(1)).save(any(Leito.class), eq(hospitalSchema), eq(pacienteSchema));
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        ILeitoRegistrationData dados = mock(ILeitoRegistrationData.class);
        UUID hospitalId = UUID.randomUUID();
        when(dados.hospital()).thenReturn(hospitalId);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(pacienteGateway, never()).findById(any());
        verify(leitoGateway, never()).save(any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar PacienteNotFoundException quando paciente não existe")
    void shouldThrowPacienteNotFoundExceptionWhenPacienteDoesNotExist() {
        ILeitoRegistrationData dados = mock(ILeitoRegistrationData.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.paciente()).thenReturn(pacienteId);

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));
        when(pacienteGateway.findById(pacienteId)).thenReturn(Optional.empty());

        assertThrows(PacienteNotFoundException.class, () -> useCase.execute(dados));
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verify(leitoGateway, never()).save(any(), any(), any());
    }

    @Test
    @DisplayName("Deve criar leito mesmo com campos de identificação vazios")
    void shouldCreateLeitoWithEmptyIdentificationFields() throws HospitalNotFoundException, PacienteNotFoundException {
        ILeitoRegistrationData dados = mock(ILeitoRegistrationData.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        PacienteSchema pacienteSchema = mock(PacienteSchema.class);
        Leito leito = mock(Leito.class);
        LeitoSchema leitoSchema = mock(LeitoSchema.class);

        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.paciente()).thenReturn(pacienteId);
        when(dados.identificacao()).thenReturn("");
        when(dados.pavilhao()).thenReturn("");
        when(dados.quarto()).thenReturn("");
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));
        when(pacienteGateway.findById(pacienteId)).thenReturn(Optional.of(pacienteSchema));
        when(hospitalSchema.toHospital()).thenReturn(mock(com.fiap.hackaton.entity.hospital.model.Hospital.class));
        when(pacienteSchema.toPaciente()).thenReturn(mock(com.fiap.hackaton.entity.paciente.model.Paciente.class));
        when(leitoGateway.save(any(Leito.class), eq(hospitalSchema), eq(pacienteSchema))).thenReturn(leitoSchema);
        when(leitoSchema.toLeito()).thenReturn(leito);

        Leito resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(leitoGateway, times(1)).save(any(Leito.class), eq(hospitalSchema), eq(pacienteSchema));
    }
}