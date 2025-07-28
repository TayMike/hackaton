package com.fiap.hackaton.usecase.core.paciente;

import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchPacienteUseCase {

    private final PacienteGateway pacienteGateway;

    public SearchPacienteUseCase(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    @Transactional(readOnly = true)
    public List<Paciente> execute() {
        return this.pacienteGateway.findAll();
    }

}
