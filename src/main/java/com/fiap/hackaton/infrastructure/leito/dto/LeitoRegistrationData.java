package com.fiap.hackaton.infrastructure.leito.dto;

import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.usecase.leito.dto.ILeitoRegistrationData;

import java.util.UUID;

public record LeitoRegistrationData(
        String identificacao,
        String pavilhao,
        String quarto,
        UUID hospital,
        UUID paciente
) implements ILeitoRegistrationData {

    public LeitoRegistrationData(Leito leito) {
        this(
                leito.getIdentificacao(),
                leito.getPavilhao(),
                leito.getQuarto(),
                leito.getHospital().getId(),
                leito.getPaciente().getId()
        );
    }
}
