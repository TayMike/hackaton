package com.fiap.hackaton.infrastructure.colaborador.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.time.LocalDateTime;

public record ColaboradorRegistrationData(
        String cpf,
        String nome,
        String matricula,
        LocalDateTime primeiroDiaCadastro,
        String cep,
        Integer numeroCasa,
        Hospital hospital,
        String setor,
        Boolean ativo
) {

    public ColaboradorRegistrationData(Colaborador colaborador) {
        this(
                colaborador.getCpf(),
                colaborador.getNome(),
                colaborador.getMatricula(),
                colaborador.getPrimeiroDiaCadastro(),
                colaborador.getCep(),
                colaborador.getNumeroCasa(),
                colaborador.getHospital(),
                colaborador.getSetor(),
                colaborador.getAtivo()
        );
    }
}