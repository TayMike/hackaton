package com.fiap.hackaton.infrastructure.core.paciente.dto;

import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.usecase.core.paciente.dto.IPacienteUpdateData;

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