package com.fiap.hackaton.infrastructure.hospital.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalPublicData;

import java.util.List;
import java.util.UUID;

public record HospitalPublicData(
        UUID id,
        List<Colaborador> colaboradores,
        String cep,
        Integer numero
) implements IHospitalPublicData {

    public HospitalPublicData(Hospital hospital) {
        this(
                hospital.getId(),
                hospital.getColaboradores(),
                hospital.getCep(),
                hospital.getNumero()
        );
    }
}