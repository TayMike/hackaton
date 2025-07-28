package com.fiap.hackaton.infrastructure.core.paciente.dto;

import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.usecase.core.paciente.dto.IPacientePublicData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record PacientePublicData(
        UUID id,
        String cpf,
        String nome,
        OffsetDateTime primeiroDiaCadastro,
        String cep,
        Integer numeroCasa
) implements IPacientePublicData {

    public PacientePublicData(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getCpf(),
                paciente.getNome(),
                paciente.getPrimeiroDiaCadastro(),
                paciente.getCep(),
                paciente.getNumeroCasa()
        );
    }
}