package com.fiap.hackaton.infrastructure.core.leito.dto;

import com.fiap.hackaton.entity.core.leito.model.Leito;
import com.fiap.hackaton.usecase.core.leito.dto.ILeitoUpdateData;

import java.util.UUID;

public record LeitoUpdateData(
        String identificacao,
        String pavilhao,
        String quarto,
        UUID hospitalId,
        UUID pacienteId
) implements ILeitoUpdateData {

    public LeitoUpdateData(Leito leito) {
        this(
                leito.getIdentificacao(),
                leito.getPavilhao(),
                leito.getQuarto(),
                leito.getHospitalId(),
                leito.getPacienteId()
        );
    }

}
