package com.fiap.hackaton.infrastructure.hospital.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalRegistrationData;

import java.util.List;

public record HospitalRegistrationData(
        List<Colaborador> colaboradores,
        String cep,
        Integer numero,
        Integer quantidadeLeitoAtual,
        Integer quantidadeLeitoMaximo
) implements IHospitalRegistrationData {

    public HospitalRegistrationData(Hospital hospital) {
        this(
                hospital.getColaboradores(),
                hospital.getCep(),
                hospital.getNumero(),
                hospital.getQuantidadeLeitoAtual(),
                hospital.getQuantidadeLeitoMaximo()
        );
    }
}