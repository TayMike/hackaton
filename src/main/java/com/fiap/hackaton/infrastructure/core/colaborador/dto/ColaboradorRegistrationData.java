package com.fiap.hackaton.infrastructure.core.colaborador.dto;

import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.usecase.core.colaborador.dto.IColaboradorRegistrationData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record ColaboradorRegistrationData(
        String cpf,
        String nome,
        String matricula,
        OffsetDateTime primeiroDiaCadastro,
        OffsetDateTime ultimoDiaCadastro,
        String cep,
        Integer numeroCasa,
        UUID hospitalId,
        String setor
) implements IColaboradorRegistrationData {

    public ColaboradorRegistrationData(Colaborador colaborador) {
        this(
                colaborador.getCpf(),
                colaborador.getNome(),
                colaborador.getMatricula(),
                colaborador.getPrimeiroDiaCadastro(),
                colaborador.getUltimoDiaCadastro(),
                colaborador.getCep(),
                colaborador.getNumeroCasa(),
                colaborador.getHospitalId(),
                colaborador.getSetor()
        );
    }
}