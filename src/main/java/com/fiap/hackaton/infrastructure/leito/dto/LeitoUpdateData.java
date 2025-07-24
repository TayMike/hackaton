package com.fiap.hackaton.infrastructure.leito.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.leito.dto.ILeitoUpdateData;

import java.util.UUID;

public record LeitoUpdateData(
        String identificacao,
        String pavilhao,
        String quarto,
        UUID hospital,
        UUID paciente
) implements ILeitoUpdateData {

    public LeitoUpdateData(Leito leito) {
        this(
                leito.getIdentificacao(),
                leito.getPavilhao(),
                leito.getQuarto(),
                leito.getHospital().getId(),
                leito.getPaciente().getId()
        );
    }

}
