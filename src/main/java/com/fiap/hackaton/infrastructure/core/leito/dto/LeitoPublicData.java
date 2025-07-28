package com.fiap.hackaton.infrastructure.core.leito.dto;

import com.fiap.hackaton.entity.core.leito.model.Leito;
import com.fiap.hackaton.usecase.core.leito.dto.ILeitoPublicData;

import java.util.UUID;

public record LeitoPublicData(
        UUID id,
        String identificacao,
        String pavilhao,
        String quarto,
        UUID hospitalId,
        UUID pacienteId
) implements ILeitoPublicData {

    public LeitoPublicData(Leito leito) {
        this(
                leito.getId(),
                leito.getIdentificacao(),
                leito.getPavilhao(),
                leito.getQuarto(),
                leito.getHospitalId(),
                leito.getPacienteId()
        );
    }
}
