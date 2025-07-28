package com.fiap.hackaton.infrastructure.core.paciente.dto;

import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.usecase.core.paciente.dto.IPacienteRegistrationData;

import java.time.OffsetDateTime;

public record PacienteRegistrationData(
        String cpf,
        String nome,
        OffsetDateTime primeiroDiaCadastro,
        String cep,
        Integer numeroCasa
) implements IPacienteRegistrationData {

    public PacienteRegistrationData(Paciente paciente) {
        this(
                paciente.getCpf(),
                paciente.getNome(),
                paciente.getPrimeiroDiaCadastro(),
                paciente.getCep(),
                paciente.getNumeroCasa()
        );
    }
}