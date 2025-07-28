package com.fiap.hackaton.infrastructure.core.hospital.dto;

import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.hospital.dto.IHospitalRegistrationData;

import java.util.List;
import java.util.UUID;

public record HospitalRegistrationData(
        String nome,
        String cnpj,
        List<UUID> colaboradoresIds,
        String cep,
        Integer numero,
        Integer quantidadeLeitoAtual,
        Integer quantidadeLeitoMaximo
) implements IHospitalRegistrationData {

    public HospitalRegistrationData(Hospital hospital) {
        this(
                hospital.getNome(),
                hospital.getCnpj(),
                hospital.getColaboradoresIds(),
                hospital.getCep(),
                hospital.getNumero(),
                hospital.getQuantidadeLeitoAtual(),
                hospital.getQuantidadeLeitoMaximo()
        );
    }
}