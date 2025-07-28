package com.fiap.hackaton.infrastructure.core.colaborador.dto;

import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.usecase.core.colaborador.dto.IColaboradorUpdateData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ColaboradorUpdateData(
        String nome,
        String cep,
        Integer numeroCasa,
        UUID hospitalId,
        String setor,
        OffsetDateTime ultimoDiaCadastro
) implements IColaboradorUpdateData {

    public ColaboradorUpdateData(Colaborador colaborador) {
        this(
                colaborador.getNome(),
                colaborador.getCep(),
                colaborador.getNumeroCasa(),
                colaborador.getHospitalId(),
                colaborador.getSetor(),
                colaborador.getUltimoDiaCadastro()
        );
    }
}