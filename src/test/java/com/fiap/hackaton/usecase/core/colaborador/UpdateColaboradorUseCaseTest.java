package com.fiap.hackaton.usecase.core.colaborador;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.colaborador.dto.IColaboradorUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateColaboradorUseCaseTest {

    private ColaboradorGateway colaboradorGateway;
    private HospitalGateway hospitalGateway;
    private UpdateColaboradorUseCase useCase;

    @BeforeEach
    void setUp() {
        colaboradorGateway = mock(ColaboradorGateway.class);
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new UpdateColaboradorUseCase(colaboradorGateway, hospitalGateway);
    }

    @Test
    @DisplayName("Deve atualizar colaborador com dados válidos")
    void shouldUpdateColaboradorWithValidData() throws HospitalNotFoundException, ColaboradorNotFoundException {
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IColaboradorUpdateData dados = mock(IColaboradorUpdateData.class);
        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.nome()).thenReturn("Novo Nome");
        when(dados.cep()).thenReturn("12345678");
        when(dados.numeroCasa()).thenReturn(200);
        when(dados.setor()).thenReturn("Novo Setor");
        when(dados.ultimoDiaCadastro()).thenReturn(null);

        Hospital hospital = mock(Hospital.class);
        when(hospital.getId()).thenReturn(hospitalId);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaborador));
        when(colaboradorGateway.update(colaborador)).thenReturn(colaborador);

        Colaborador result = useCase.execute(colaboradorId, dados);

        assertNotNull(result);
        verify(hospitalGateway).findById(hospitalId);
        verify(colaboradorGateway).findById(colaboradorId);
        verify(colaborador).setNome("Novo Nome");
        verify(colaborador).setCep("12345678");
        verify(colaborador).setNumeroCasa(200);
        verify(colaborador).setSetor("Novo Setor");
        verify(colaborador).setHospitalId(hospitalId);
        verify(colaboradorGateway).update(colaborador);
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IColaboradorUpdateData dados = mock(IColaboradorUpdateData.class);
        when(dados.hospitalId()).thenReturn(hospitalId);

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(colaboradorId, dados));
        verify(hospitalGateway).findById(hospitalId);
        verifyNoInteractions(colaboradorGateway);
    }

    @Test
    @DisplayName("Deve lançar ColaboradorNotFoundException quando colaborador não existe")
    void shouldThrowColaboradorNotFoundExceptionWhenColaboradorDoesNotExist() {
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IColaboradorUpdateData dados = mock(IColaboradorUpdateData.class);
        when(dados.hospitalId()).thenReturn(hospitalId);

        Hospital hospital = mock(Hospital.class);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));

        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(colaboradorId, dados));
        verify(hospitalGateway).findById(hospitalId);
        verify(colaboradorGateway).findById(colaboradorId);
    }

    @Test
    @DisplayName("Deve atualizar apenas campos não nulos e não vazios")
    void shouldUpdateOnlyNonNullAndNonBlankFields() throws HospitalNotFoundException, ColaboradorNotFoundException {
        UUID colaboradorId = UUID.randomUUID();
        UUID hospitalId = UUID.randomUUID();

        IColaboradorUpdateData dados = mock(IColaboradorUpdateData.class);
        when(dados.hospitalId()).thenReturn(hospitalId);
        when(dados.nome()).thenReturn("");
        when(dados.cep()).thenReturn(null);
        when(dados.numeroCasa()).thenReturn(null);
        when(dados.setor()).thenReturn(" ");
        when(dados.ultimoDiaCadastro()).thenReturn(null);

        Hospital hospital = mock(Hospital.class);
        when(hospital.getId()).thenReturn(hospitalId);
        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));

        Colaborador colaborador = mock(Colaborador.class);
        when(colaboradorGateway.findById(colaboradorId)).thenReturn(Optional.of(colaborador));
        when(colaboradorGateway.update(colaborador)).thenReturn(colaborador);

        Colaborador result = useCase.execute(colaboradorId, dados);

        assertNotNull(result);
        verify(colaborador, never()).setNome(any());
        verify(colaborador, never()).setCep(any());
        verify(colaborador, never()).setNumeroCasa(any());
        verify(colaborador, never()).setSetor(any());
        verify(colaborador, never()).setUltimoDiaCadastro(any());
        verify(colaborador).setHospitalId(hospitalId);
        verify(colaboradorGateway).update(colaborador);
    }
}