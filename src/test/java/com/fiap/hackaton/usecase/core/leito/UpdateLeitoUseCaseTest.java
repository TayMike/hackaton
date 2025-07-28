package com.fiap.hackaton.usecase.core.leito;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.leito.model.Leito;
import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.usecase.core.leito.dto.ILeitoUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Leito leito = mock(Leito.class);

        when(leitoGateway.findById(id)).thenReturn(Optional.of(leito));

        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.pacienteId()).thenReturn(pacienteId);
        when(dados.identificacao()).thenReturn("A101");
        when(dados.pavilhao()).thenReturn("Pavilhão 1");
        when(dados.quarto()).thenReturn("Quarto 10");

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(mock()));
        when(pacienteGateway.findById(pacienteId)).thenReturn(Optional.of(mock()));
        when(leitoGateway.update(leito)).thenReturn(leito);

        Leito resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(leito).setIdentificacao("A101");
        verify(leito).setPavilhao("Pavilhão 1");
        verify(leito).setQuarto("Quarto 10");
        verify(leito).setHospitalId(hospitalId);
        verify(leito).setPacienteId(pacienteId);
        verify(leitoGateway, times(1)).update(leito);
    }

    @Test
    @DisplayName("Deve lançar LeitoNotFoundException quando leito não existe")
    void shouldThrowLeitoNotFoundExceptionWhenLeitoDoesNotExist() {
        UUID id = UUID.randomUUID();
        ILeitoUpdateData dados = mock(ILeitoUpdateData.class);

        when(leitoGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(LeitoNotFoundException.class, () -> useCase.execute(id, dados));
        verify(leitoGateway, times(1)).findById(id);
        verify(leitoGateway, never()).update(any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID id = UUID.randomUUID();
        ILeitoUpdateData dados = mock(ILeitoUpdateData.class);
        Leito leito = mock(Leito.class);
        UUID hospitalId = UUID.randomUUID();

        when(leitoGateway.findById(id)).thenReturn(Optional.of(leito));
        when(dados.hospitalId()).thenReturn(hospitalId);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(id, dados));
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(leitoGateway, never()).update(any());
    }

    @Test
    @DisplayName("Deve lançar PacienteNotFoundException quando paciente não existe")
    void shouldThrowPacienteNotFoundExceptionWhenPacienteDoesNotExist() {
        UUID id = UUID.randomUUID();
        ILeitoUpdateData dados = mock(ILeitoUpdateData.class);
        Leito leito = mock(Leito.class);
        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(leitoGateway.findById(id)).thenReturn(Optional.of(leito));
        when(dados.hospitalId()).thenReturn(hospitalId);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(mock()));
        when(dados.pacienteId()).thenReturn(pacienteId);
        when(pacienteGateway.findById(pacienteId)).thenReturn(Optional.empty());

        assertThrows(PacienteNotFoundException.class, () -> useCase.execute(id, dados));
        verify(pacienteGateway, times(1)).findById(pacienteId);
        verify(leitoGateway, never()).update(any());
    }

    @Test
    @DisplayName("Deve ignorar atualização de campos quando valores nulos ou em branco são fornecidos")
    void shouldIgnoreUpdateWhenFieldsAreNullOrBlank() throws LeitoNotFoundException, PacienteNotFoundException, HospitalNotFoundException {
        UUID id = UUID.randomUUID();
        ILeitoUpdateData dados = mock(ILeitoUpdateData.class);
        Leito leito = mock(Leito.class);

        when(leitoGateway.findById(id)).thenReturn(Optional.of(leito));

        UUID hospitalId = UUID.randomUUID();
        UUID pacienteId = UUID.randomUUID();

        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.pacienteId()).thenReturn(pacienteId);
        when(dados.identificacao()).thenReturn(" ");
        when(dados.pavilhao()).thenReturn(null);
        when(dados.quarto()).thenReturn("");

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(mock()));
        when(pacienteGateway.findById(pacienteId)).thenReturn(Optional.of(mock()));
        when(leitoGateway.update(leito)).thenReturn(leito);

        Leito resultado = useCase.execute(id, dados);

        assertNotNull(resultado);
        verify(leito, never()).setIdentificacao(any());
        verify(leito, never()).setPavilhao(any());
        verify(leito, never()).setQuarto(any());
        verify(leito).setHospitalId(hospitalId);
        verify(leito).setPacienteId(pacienteId);
        verify(leitoGateway, times(1)).update(leito);
    }
}