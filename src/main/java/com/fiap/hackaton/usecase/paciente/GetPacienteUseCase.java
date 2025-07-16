package com.fiap.hackaton.usecase.paciente;

import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.paciente.model.Paciente;

import java.util.UUID;

public class GetPacienteUseCase {

    private final PacienteGateway pacienteGateway;

    public GetPacienteUseCase(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    public Paciente execute(UUID id) throws PacienteNotFoundException {
        return this.pacienteGateway.findById(id).
                orElseThrow(PacienteNotFoundException::new);
    }

}
