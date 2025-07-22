package com.fiap.hackaton.usecase.leito;

import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.LeitoSchema;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.leito.dto.ILeitoUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateLeitoUseCaseTest {

    private LeitoGateway leitoGateway;
    private HospitalGateway hospitalGateway;
    private PacienteGateway pacienteGateway;
    private UpdateLeitoUseCase useCase;

    @BeforeEach
    void setUp() {
        leitoGateway = mock(LeitoGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        pacienteGateway = mock(PacienteGateway.class);
        useCase = new UpdateLeitoUseCase(leitoGateway, hospitalGateway, pacienteGateway);
    }

    @Test
    @DisplayName("Deve atualizar todos os campos do leito quando dados válidos são fornecidos")
    void shouldUpdateAllFieldsWhenValidDataProvided() throws LeitoNotFoundException, PacienteNotFoundException, HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        ILeitoUpdateData dados = mock(ILeitoUpdateData.class);
        LeitoSchema leitoSchema = mock(LeitoSchema.class);
        Leito leito = mock(Leito.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        PacienteSchema pacienteSchema = mock(PacienteSchema.class);
        Hospital hospital = mock(Hospital.class);
        Paciente paciente = mock(Paciente.class);

        when(leitoGateway.findById(id)).thenReturn(Optional.of(leitoSchema));
        when(leitoSchema.toLeito()).thenReturn(leito);
        when(hospitalGateway.findById(any())).thenReturn(Optional.of(hospitalSchema));
        when(pacienteGateway.findById(any())).thenReturn(Optional.of(pacienteSchema));
        when(hospitalSchema.toHospital()).thenReturn(hospital);
        when(pacienteSchema.toPaciente()).thenReturn(paciente);
        when(leitoGateway.update(leito, hospitalSchema, pacienteSchema)).thenReturn(leitoSchema);
        when(leitoSchema.toLeito()).thenReturn(leito);

        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(dados.hospital()).thenReturn(hospital);
        when(dados.paciente()).thenReturn(paciente);
        when(dados.identificacao()).thenReturn("A101");
        when(dados.pavilhao()).thenReturn("Pavilhão 1");
        when(dados.quarto()).thenReturn("Quarto 10");
        when(hospital.getId()).thenReturn(hospitalId);
        when(paciente.getId()).thenReturn(pacienteId);

        Leito resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(leito).setIdentificacao("A101");
        verify(leito).setPavilhao("Pavilhão 1");
        verify(leito).setQuarto("Quarto 10");
        verify(leito).setHospital(hospital);
        verify(leito).setPaciente(paciente);
        verify(leitoGateway, times(1)).update(leito, hospitalSchema, pacienteSchema);
    }

    @Test
    @DisplayName("Deve lançar LeitoNotFoundException quando leito não existe")
    void shouldThrowLeitoNotFoundExceptionWhenLeitoDoesNotExist() {
        UUID id = UUID.randomUUID();
        ILeitoUpdateData dados = mock(ILeitoUpdateData.class);

        when(leitoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(LeitoNotFoundException.class, () -> useCase.execute(id, dados));
        verify(leitoGateway, times(1)).findById(id);
        verify(leitoGateway, never()).update(any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID id = UUID.randomUUID();
        ILeitoUpdateData dados = mock(ILeitoUpdateData.class);
        LeitoSchema leitoSchema = mock(LeitoSchema.class);
        Leito leito = mock(Leito.class);
        Hospital hospital = mock(Hospital.class);
        UUID hospitalId = UUID.randomUUID();

        when(leitoGateway.findById(id)).thenReturn(Optional.of(leitoSchema));
        when(leitoSchema.toLeito()).thenReturn(leito);
        when(dados.hospital()).thenReturn(hospital);
        when(hospital.getId()).thenReturn(hospitalId);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(id, dados));
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(leitoGateway, never()).update(any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar PacienteNotFoundException quando paciente não existe")
    void shouldThrowPacienteNotFoundExceptionWhenPacienteDoesNotExist() {
        UUID id = UUID.randomUUID();
        ILeitoUpdateData dados = mock(ILeitoUpdateData.class);
        LeitoSchema leitoSchema = mock(LeitoSchema.class);
        Leito leito = mock(Leito.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        Hospital hospital = mock(Hospital.class);
        Paciente paciente = mock(Paciente.class);
        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(leitoGateway.findById(id)).thenReturn(Optional.of(leitoSchema));
        when(leitoSchema.toLeito()).thenReturn(leito);
        when(dados.hospital()).thenReturn(hospital);
        when(hospital.getId()).thenReturn(hospitalId);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospitalSchema));
        when(dados.paciente()).thenReturn(paciente);
        when(paciente.getId()).thenReturn(pacienteId);
        when(pacienteGateway.findById(pacienteId)).thenReturn(Optional.empty());

        assertThrows(PacienteNotFoundException.class, () -> useCase.execute(id, dados));
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verify(leitoGateway, never()).update(any(), any(), any());
    }

    @Test
    @DisplayName("Deve ignorar atualização de campos quando valores nulos ou em branco são fornecidos")
    void shouldIgnoreUpdateWhenFieldsAreNullOrBlank() throws LeitoNotFoundException, PacienteNotFoundException, HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        ILeitoUpdateData dados = mock(ILeitoUpdateData.class);
        LeitoSchema leitoSchema = mock(LeitoSchema.class);
        Leito leito = mock(Leito.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        PacienteSchema pacienteSchema = mock(PacienteSchema.class);
        Hospital hospital = mock(Hospital.class);
        Paciente paciente = mock(Paciente.class);

        when(leitoGateway.findById(id)).thenReturn(Optional.of(leitoSchema));
        when(leitoSchema.toLeito()).thenReturn(leito);
        when(hospitalGateway.findById(any())).thenReturn(Optional.of(hospitalSchema));
        when(pacienteGateway.findById(any())).thenReturn(Optional.of(pacienteSchema));
        when(hospitalSchema.toHospital()).thenReturn(hospital);
        when(pacienteSchema.toPaciente()).thenReturn(paciente);
        when(leitoGateway.update(leito, hospitalSchema, pacienteSchema)).thenReturn(leitoSchema);
        when(leitoSchema.toLeito()).thenReturn(leito);

        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(dados.hospital()).thenReturn(hospital);
        when(dados.paciente()).thenReturn(paciente);
        when(dados.identificacao()).thenReturn(" ");
        when(dados.pavilhao()).thenReturn(null);
        when(dados.quarto()).thenReturn("");
        when(hospital.getId()).thenReturn(hospitalId);
        when(paciente.getId()).thenReturn(pacienteId);

        Leito resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(leito, never()).setIdentificacao(any());
        verify(leito, never()).setPavilhao(any());
        verify(leito, never()).setQuarto(any());
        verify(leito).setHospital(hospital);
        verify(leito).setPaciente(paciente);
        verify(leitoGateway, times(1)).update(leito, hospitalSchema, pacienteSchema);
    }
}