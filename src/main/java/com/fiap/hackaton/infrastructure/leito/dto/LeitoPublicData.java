package com.fiap.hackaton.infrastructure.leito.dto;

import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.usecase.leito.dto.ILeitoPublicData;

import java.util.UUID;

public record LeitoPublicData(
        UUID id,
        String identificacao,
        String pavilhao,
        String quarto,
        UUID hospital,
        UUID paciente
) implements ILeitoPublicData {

    public LeitoPublicData(Leito leito) {
        this(
                leito.getId(),
                leito.getIdentificacao(),
                leito.getPavilhao(),
                leito.getQuarto(),
                leito.getHospital().getId(),
                leito.getPaciente().getId()
        );
    }
}
