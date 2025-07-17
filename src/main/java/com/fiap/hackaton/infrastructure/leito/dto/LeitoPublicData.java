package com.fiap.hackaton.infrastructure.leito.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.leito.dto.ILeitoPublicData;

import java.util.UUID;

public record LeitoPublicData(
        UUID id,
        String identificacao,
        String pavilhao,
        String quarto,
        Hospital hospital,
        Paciente paciente
) implements ILeitoPublicData {

    public LeitoPublicData(Leito leito) {
        this(
                leito.getId(),
                leito.getIdentificacao(),
                leito.getPavilhao(),
                leito.getQuarto(),
                leito.getHospital(),
                leito.getPaciente()
        );
    }
}
