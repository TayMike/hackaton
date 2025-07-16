package com.fiap.hackaton.infrastructure.hospital.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalUpdateData;

import java.util.List;

public record HospitalUpdateData(
        List<Colaborador> colaboradores,
        String cep,
        Integer numero
) implements IHospitalUpdateData {

    public HospitalUpdateData(Hospital hospital) {
        this(
                hospital.getColaboradores(),
                hospital.getCep(),
                hospital.getNumero()
        );
    }
}