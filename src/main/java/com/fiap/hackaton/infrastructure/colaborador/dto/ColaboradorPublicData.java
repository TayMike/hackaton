package com.fiap.hackaton.infrastructure.colaborador.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.usecase.colaborador.dto.IColaboradorPublicData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ColaboradorPublicData(
        UUID id,
        String cpf,
        String nome,
        String matricula,
        OffsetDateTime primeiroDiaCadastro,
        String cep,
        Integer numeroCasa,
        UUID hospital,
        String setor,
        Boolean ativo
) implements IColaboradorPublicData {

    public ColaboradorPublicData(Colaborador colaborador) {
        this(
                colaborador.getId(),
                colaborador.getCpf(),
                colaborador.getNome(),
                colaborador.getMatricula(),
                colaborador.getPrimeiroDiaCadastro(),
                colaborador.getCep(),
                colaborador.getNumeroCasa(),
                colaborador.getHospital().getId(),
                colaborador.getSetor(),
                colaborador.getAtivo()
        );
    }
}
