package com.fiap.hackaton.usecase.coletaInsumo;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.infrastructure.config.db.schema.*;
import com.fiap.hackaton.usecase.coletaInsumo.dto.IColetaInsumoRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Collections;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateColetaInsumoUseCaseTest {

    private ColetaInsumoGateway coletaInsumoGateway;
    private ColaboradorGateway colaboradorGateway;
    private PacienteGateway pacienteGateway;
    private HospitalGateway hospitalGateway;
    private InsumoGateway insumoGateway;
    private CreateColetaInsumoUseCase useCase;

    @BeforeEach
    void setUp() {
        coletaInsumoGateway = mock(ColetaInsumoGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        pacienteGateway = mock(PacienteGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        insumoGateway = mock(InsumoGateway.class);
        useCase = new CreateColetaInsumoUseCase(coletaInsumoGateway, colaboradorGateway, pacienteGateway, hospitalGateway, insumoGateway);
    }

    @Test
    @DisplayName("Deve criar coleta de insumo com dados válidos")
    void shouldCreateColetaInsumoWithValidData() throws HospitalNotFoundException, PacienteNotFoundException, ColaboradorNotFoundException {
        UUID colaboradorId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();
        List<UUID> insumoIds = List.of(UUID.randomUUID(), UUID.randomUUID());
        List<Long> quantidades = List.of(2L, 3L);
        OffsetDateTime dataHora = OffsetDateTime.now();

        IColetaInsumoRegistrationData dados = mock(IColetaInsumoRegistrationData.class);
        when(dados.colaboradorEntregador()).thenReturn(colaboradorId);
        when(dados.pacienteRecebedor()).thenReturn(pacienteId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.insumos()).thenReturn(insumoIds);
        when(dados.quantidades()).thenReturn(quantidades);
        when(dados.dataHoraColeta()).thenReturn(dataHora);

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        PacienteSchema pacienteSchema = mock(PacienteSchema.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);
        InsumoSchema insumoSchema1 = mock(InsumoSchema.class);
        InsumoSchema insumoSchema2 = mock(InsumoSchema.class);

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(java.util.Optional.of(colaboradorSchema));
        when(pacienteGateway.findById(pacienteId)).thenReturn(java.util.Optional.of(pacienteSchema));
        when(hospitalGateway.findById(hospitalId)).thenReturn(java.util.Optional.of(hospitalSchema));
        when(insumoGateway.findAll()).thenReturn(List.of(insumoSchema1, insumoSchema2));
        when(insumoSchema1.getId()).thenReturn(insumoIds.get(0));
        when(insumoSchema2.getId()).thenReturn(insumoIds.get(1));
        when(insumoSchema1.toInsumo()).thenReturn(mock(com.fiap.hackaton.entity.insumo.model.Insumo.class));
        when(insumoSchema2.toInsumo()).thenReturn(mock(com.fiap.hackaton.entity.insumo.model.Insumo.class));
        when(colaboradorSchema.toColaborador()).thenReturn(mock(com.fiap.hackaton.entity.colaborador.model.Colaborador.class));
        when(pacienteSchema.toPaciente()).thenReturn(mock(com.fiap.hackaton.entity.paciente.model.Paciente.class));
        when(hospitalSchema.toHospital()).thenReturn(mock(com.fiap.hackaton.entity.hospital.model.Hospital.class));

        ColetaInsumoSchema coletaSchemaInsumo = mock(ColetaInsumoSchema.class);
        ColetaInsumo coletaInsumo = mock(ColetaInsumo.class);
        when(coletaInsumoGateway.save(any(), any(), any(), any(), any())).thenReturn(coletaSchemaInsumo);
        when(coletaSchemaInsumo.toColeta()).thenReturn(coletaInsumo);

        ColetaInsumo resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(insumoGateway, times(1)).findAll();
        verify(coletaInsumoGateway, times(1)).save(any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName("Deve lançar ColaboradorNotFoundException quando colaborador não existe")
    void shouldThrowColaboradorNotFoundExceptionWhenColaboradorDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        IColetaInsumoRegistrationData dados = mock(IColetaInsumoRegistrationData.class);
        when(dados.colaboradorEntregador()).thenReturn(colaboradorId);

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(java.util.Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verifyNoInteractions(pacienteGateway, hospitalGateway, insumoGateway, coletaInsumoGateway);
    }

    @Test
    @DisplayName("Deve lançar PacienteNotFoundException quando paciente não existe")
    void shouldThrowPacienteNotFoundExceptionWhenPacienteDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        IColetaInsumoRegistrationData dados = mock(IColetaInsumoRegistrationData.class);
        when(dados.colaboradorEntregador()).thenReturn(colaboradorId);
        when(dados.pacienteRecebedor()).thenReturn(pacienteId);

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(java.util.Optional.of(colaboradorSchema));
        when(pacienteGateway.findById(pacienteId)).thenReturn(java.util.Optional.empty());

        assertThrows(PacienteNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verifyNoInteractions(hospitalGateway, insumoGateway, coletaInsumoGateway);
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();
        IColetaInsumoRegistrationData dados = mock(IColetaInsumoRegistrationData.class);
        when(dados.colaboradorEntregador()).thenReturn(colaboradorId);
        when(dados.pacienteRecebedor()).thenReturn(pacienteId);
        when(dados.hospital()).thenReturn(hospitalId);

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        PacienteSchema pacienteSchema = mock(PacienteSchema.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(java.util.Optional.of(colaboradorSchema));
        when(pacienteGateway.findById(pacienteId)).thenReturn(java.util.Optional.of(pacienteSchema));
        when(hospitalGateway.findById(hospitalId)).thenReturn(java.util.Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(dados));
        verify(colaboradorGateway, times(1)).findById(colaboradorId);
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verifyNoInteractions(insumoGateway, coletaInsumoGateway);
    }

    @Test
    @DisplayName("Deve criar coleta de insumo com lista de insumos vazia")
    void shouldCreateColetaInsumoWithEmptyInsumosList() throws HospitalNotFoundException, PacienteNotFoundException, ColaboradorNotFoundException {
        UUID colaboradorId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();
        List<UUID> insumoIds = Collections.emptyList();
        List<Long> quantidades = Collections.emptyList();
        OffsetDateTime dataHora = OffsetDateTime.now();

        IColetaInsumoRegistrationData dados = mock(IColetaInsumoRegistrationData.class);
        when(dados.colaboradorEntregador()).thenReturn(colaboradorId);
        when(dados.pacienteRecebedor()).thenReturn(pacienteId);
        when(dados.hospital()).thenReturn(hospitalId);
        when(dados.insumos()).thenReturn(insumoIds);
        when(dados.quantidades()).thenReturn(quantidades);
        when(dados.dataHoraColeta()).thenReturn(dataHora);

        ColaboradorSchema colaboradorSchema = mock(ColaboradorSchema.class);
        PacienteSchema pacienteSchema = mock(PacienteSchema.class);
        HospitalSchema hospitalSchema = mock(HospitalSchema.class);

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(java.util.Optional.of(colaboradorSchema));
        when(pacienteGateway.findById(pacienteId)).thenReturn(java.util.Optional.of(pacienteSchema));
        when(hospitalGateway.findById(hospitalId)).thenReturn(java.util.Optional.of(hospitalSchema));
        when(insumoGateway.findAll()).thenReturn(Collections.emptyList());
        when(colaboradorSchema.toColaborador()).thenReturn(mock(com.fiap.hackaton.entity.colaborador.model.Colaborador.class));
        when(pacienteSchema.toPaciente()).thenReturn(mock(com.fiap.hackaton.entity.paciente.model.Paciente.class));
        when(hospitalSchema.toHospital()).thenReturn(mock(com.fiap.hackaton.entity.hospital.model.Hospital.class));

        ColetaInsumoSchema coletaSchemaInsumo = mock(ColetaInsumoSchema.class);
        ColetaInsumo coletaInsumo = mock(ColetaInsumo.class);
        when(coletaInsumoGateway.save(any(), any(), any(), any(), any())).thenReturn(coletaSchemaInsumo);
        when(coletaSchemaInsumo.toColeta()).thenReturn(coletaInsumo);

        ColetaInsumo resultado = useCase.execute(dados);

        assertNotNull(resultado);
        verify(insumoGateway, times(1)).findAll();
        verify(coletaInsumoGateway, times(1)).save(any(), any(), any(), any(), any());
    }
}