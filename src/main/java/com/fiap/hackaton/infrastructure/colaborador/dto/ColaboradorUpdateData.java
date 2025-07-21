package com.fiap.hackaton.infrastructure.colaborador.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.usecase.colaborador.dto.IColaboradorUpdateData;

import java.util.UUID;

public record ColaboradorUpdateData(
        String nome,
        String cep,
        Integer numeroCasa,
        UUID hospital,
        String setor,
        Boolean ativo
) implements IColaboradorUpdateData {

    public ColaboradorUpdateData(Colaborador colaborador) {
        this(
                colaborador.getNome(),
                colaborador.getCep(),
                colaborador.getNumeroCasa(),
                colaborador.getHospital().getId(),
                colaborador.getSetor(),
                colaborador.getAtivo()
        );
    }
}