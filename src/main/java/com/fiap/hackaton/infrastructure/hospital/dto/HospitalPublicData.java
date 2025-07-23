package com.fiap.hackaton.infrastructure.hospital.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.hospital.dto.IHospitalPublicData;

import java.util.List;
import java.util.UUID;

public record HospitalPublicData(
        UUID id,
        String nome,
        String cnpj,
        List<UUID> colaboradores,
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
                hospital.getColaboradores().stream()
                        .map(Colaborador::getId)
                        .toList(),
                hospital.getCep(),
                hospital.getNumero(),
                hospital.getQuantidadeLeitoAtual(),
                hospital.getQuantidadeLeitoMaximo()
        );
    }
}