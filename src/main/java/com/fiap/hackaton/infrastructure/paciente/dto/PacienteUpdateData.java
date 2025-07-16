package com.fiap.hackaton.infrastructure.paciente.dto;

import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.paciente.dto.IPacienteUpdateData;

public record PacienteUpdateData(
        String nome,
        String cep,
        Integer numeroCasa
) implements IPacienteUpdateData {

    public PacienteUpdateData(Paciente paciente) {
        this(
                paciente.getNome(),
                paciente.getCep(),
                paciente.getNumeroCasa()
        );
    }
}