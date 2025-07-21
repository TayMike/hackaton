package com.fiap.hackaton.infrastructure.paciente.dto;

import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.paciente.dto.IPacienteRegistrationData;

import java.time.LocalDateTime;
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