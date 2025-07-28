package com.fiap.hackaton.infrastructure.core.hospital.dto;

import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.usecase.core.hospital.dto.IHospitalPublicData;

import java.util.List;
import java.util.UUID;

public record HospitalPublicData(
        UUID id,
        String nome,
        String cnpj,
        List<UUID> colaboradoresIds,
        String cep,
        Integer numero,
        Integer quantidadeLeitoAtual,
        Integer quantidadeLeitoMaximo
) implements IHospitalPublicData {

    public HospitalPublicData(Hospital hospital) {
        this(
                hospital.getId(),
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