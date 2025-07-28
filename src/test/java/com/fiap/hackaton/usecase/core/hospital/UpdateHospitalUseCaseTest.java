package com.fiap.hackaton.usecase.core.hospital;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.hospital.dto.IHospitalUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateHospitalUseCaseTest {

    private HospitalGateway hospitalGateway;
    private UpdateHospitalUseCase useCase;

    @BeforeEach
    void setUp() {
        hospitalGateway = mock(HospitalGateway.class);
        useCase = new UpdateHospitalUseCase(hospitalGateway);
    }

    @Test
    @DisplayName("Deve atualizar hospital com todos os campos válidos")
    void shouldUpdateHospitalWithAllValidFields() throws HospitalNotFoundException {
        UUID hospitalId = UUID.randomUUID();
        IHospitalUpdateData dados = mock(IHospitalUpdateData.class);

        when(dados.cep()).thenReturn("12345678");
        when(dados.numero()).thenReturn(100);
        when(dados.quantidadeLeitoAtual()).thenReturn(10);
        when(dados.quantidadeLeitoMaximo()).thenReturn(20);

        Hospital hospital = mock(Hospital.class);
        var colaboradores = List.of(UUID.randomUUID());
        when(hospital.getColaboradoresIds()).thenReturn(colaboradores);

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));
        when(hospitalGateway.update(hospital)).thenReturn(hospital);

        Hospital resultado = useCase.execute(hospitalId, dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(hospitalGateway, times(1)).update(hospital);
        verify(hospital, times(2)).getColaboradoresIds();
        verify(hospital, times(1)).setColaboradoresIds(colaboradores);
        verify(hospital, times(1)).setCep("12345678");
        verify(hospital, times(1)).setNumero(100);
        verify(hospital, times(1)).setQuantidadeLeitoAtual(10);
        verify(hospital, times(1)).setQuantidadeLeitoMaximo(20);
    }

    @Test
    @DisplayName("Deve atualizar hospital ignorando campos nulos ou em branco")
    void shouldUpdateHospitalIgnoringNullOrBlankFields() throws HospitalNotFoundException {
        UUID hospitalId = UUID.randomUUID();
        IHospitalUpdateData dados = mock(IHospitalUpdateData.class);

        when(dados.cep()).thenReturn("");
        when(dados.numero()).thenReturn(null);
        when(dados.quantidadeLeitoAtual()).thenReturn(null);
        when(dados.quantidadeLeitoMaximo()).thenReturn(null);

        Hospital hospital = mock(Hospital.class);
        when(hospital.getColaboradoresIds()).thenReturn(Collections.emptyList());

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));
        when(hospitalGateway.update(any())).thenReturn(hospital);

        Hospital resultado = useCase.execute(hospitalId, dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(hospitalGateway, times(1)).update(any());
        verify(hospital, times(1)).getColaboradoresIds();
        verify(hospital, never()).setCep(anyString());
        verify(hospital, never()).setNumero(any());
        verify(hospital, never()).setQuantidadeLeitoAtual(any());
        verify(hospital, never()).setQuantidadeLeitoMaximo(any());
        verify(hospital, never()).setColaboradoresIds(any());
    }

    @Test
    @DisplayName("Deve lançar HospitalNotFoundException quando hospital não existe")
    void shouldThrowHospitalNotFoundExceptionWhenHospitalDoesNotExist() {
        UUID hospitalId = UUID.randomUUID();
        IHospitalUpdateData dados = mock(IHospitalUpdateData.class);

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.empty());

        assertThrows(HospitalNotFoundException.class, () -> useCase.execute(hospitalId, dados));
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(hospitalGateway, never()).update(any());
    }

    @Test
    @DisplayName("Deve atualizar hospital com colaboradores vazios")
    void shouldUpdateHospitalWithEmptyColaboradores() throws HospitalNotFoundException {
        UUID hospitalId = UUID.randomUUID();
        IHospitalUpdateData dados = mock(IHospitalUpdateData.class);

        when(dados.cep()).thenReturn("87654321");
        when(dados.numero()).thenReturn(200);
        when(dados.quantidadeLeitoAtual()).thenReturn(5);
        when(dados.quantidadeLeitoMaximo()).thenReturn(10);

        Hospital hospital = mock(Hospital.class);
        when(hospital.getColaboradoresIds()).thenReturn(Collections.emptyList());

        when(hospitalGateway.findById(hospitalId)).thenReturn(Optional.of(hospital));
        when(hospitalGateway.update(any())).thenReturn(hospital);

        Hospital resultado = useCase.execute(hospitalId, dados);

        assertNotNull(resultado);
        verify(hospitalGateway, times(1)).findById(hospitalId);
        verify(hospitalGateway, times(1)).update(any());
        verify(hospital, times(1)).getColaboradoresIds();
        verify(hospital, times(1)).setCep("87654321");
        verify(hospital, times(1)).setNumero(200);
        verify(hospital, times(1)).setQuantidadeLeitoAtual(5);
        verify(hospital, times(1)).setQuantidadeLeitoMaximo(10);
        verify(hospital, never()).setColaboradoresIds(any());
    }
}