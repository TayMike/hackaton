package com.fiap.hackaton.usecase.core.paciente;

import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetPacienteUseCase {

    private final PacienteGateway pacienteGateway;

    public GetPacienteUseCase(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    @Transactional(readOnly = true)
    public Paciente execute(UUID id) throws PacienteNotFoundException {
        return this.pacienteGateway.findById(id).
                orElseThrow(() -> new PacienteNotFoundException("Paciente not found: " + id));
    }

}
