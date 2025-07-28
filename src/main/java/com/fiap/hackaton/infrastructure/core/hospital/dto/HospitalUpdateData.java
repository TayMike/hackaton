package com.fiap.hackaton.infrastructure.core.hospital.dto;

import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.hospital.dto.IHospitalUpdateData;

import java.util.List;
import java.util.UUID;

public record HospitalUpdateData(
        List<UUID> colaboradoresIds,
        String cep,
        Integer numero,
        Integer quantidadeLeitoAtual,
        Integer quantidadeLeitoMaximo
) implements IHospitalUpdateData {

    public HospitalUpdateData(Hospital hospital) {
        this(
                hospital.getColaboradoresIds(),
                hospital.getCep(),
                hospital.getNumero(),
                hospital.getQuantidadeLeitoAtual(),
                hospital.getQuantidadeLeitoMaximo()
        );
    }
}