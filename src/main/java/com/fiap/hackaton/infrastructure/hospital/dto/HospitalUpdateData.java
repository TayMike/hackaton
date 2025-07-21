package com.fiap.hackaton.infrastructure.hospital.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalUpdateData;

import java.util.List;
import java.util.UUID;

public record HospitalUpdateData(
        List<UUID> colaboradores,
        String cep,
        Integer numero,
        Integer quantidadeLeitoAtual,
        Integer quantidadeLeitoMaximo
) implements IHospitalUpdateData {

    public HospitalUpdateData(Hospital hospital) {
        this(
                hospital.getColaboradores().stream().map(Colaborador::getId).toList(),
                hospital.getCep(),
                hospital.getNumero(),
                hospital.getQuantidadeLeitoAtual(),
                hospital.getQuantidadeLeitoMaximo()
        );
    }
}