package com.fiap.hackaton.infrastructure.core.leito.dto;

import com.fiap.hackaton.entity.core.leito.model.Leito;
import com.fiap.hackaton.usecase.core.leito.dto.ILeitoRegistrationData;

import java.util.UUID;

public record LeitoRegistrationData(
        String identificacao,
        String pavilhao,
        String quarto,
        UUID hospitalId,
        UUID pacienteId
) implements ILeitoRegistrationData {

    public LeitoRegistrationData(Leito leito) {
        this(
                leito.getIdentificacao(),
                leito.getPavilhao(),
                leito.getQuarto(),
                leito.getHospitalId(),
                leito.getPacienteId()
        );
    }
}
