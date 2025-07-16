package com.fiap.hackaton.infrastructure.colaborador.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.colaborador.dto.IColaboradorPublicData;

import java.time.LocalDateTime;
import java.util.UUID;

public record ColaboradorPublicData(
        UUID id,
        String cpf,
        String nome,
        String matricula,
        LocalDateTime primeiroDiaCadastro,
        String cep,
        Integer numeroCasa,
        Hospital hospital,
        String setor
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
                colaborador.getHospital(),
                colaborador.getSetor()
        );
    }
}
