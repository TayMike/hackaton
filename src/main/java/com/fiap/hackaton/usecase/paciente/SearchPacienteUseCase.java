package com.fiap.hackaton.usecase.paciente;

import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.paciente.model.Paciente;

import java.util.List;

public class SearchPacienteUseCase {

    private final PacienteGateway pacienteGateway;

    public SearchPacienteUseCase(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    public List<Paciente> execute() {
        return this.pacienteGateway.findAll();
    }

}
