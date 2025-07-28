package com.fiap.hackaton.infrastructure.core.colaborador.dto;

import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.usecase.core.colaborador.dto.IColaboradorPublicData;

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
        UUID hospitalId,
        String setor,
        OffsetDateTime ultimoDiaCadastro
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
                colaborador.getHospitalId(),
                colaborador.getSetor(),
                colaborador.getUltimoDiaCadastro()
        );
    }
}
