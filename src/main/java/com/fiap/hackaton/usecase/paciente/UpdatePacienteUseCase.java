package com.fiap.hackaton.usecase.paciente;

import com.fiap.hackaton.entity.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.paciente.dto.IPacienteUpdateData;

import java.util.UUID;

public class UpdatePacienteUseCase {

    private final PacienteGateway pacienteGateway;

    public UpdatePacienteUseCase(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    public Paciente execute(UUID id, IPacienteUpdateData dados) throws PacienteNotFoundException {
        Paciente paciente = this.pacienteGateway.findById(id)
                .orElseThrow(PacienteNotFoundException::new).toPaciente();

        if (dados.nome() != null && !dados.nome().isBlank())
            paciente.setNome(dados.nome());

        if (dados.cep() != null && !dados.cep().isBlank())
            paciente.setCep(dados.cep());

        if (dados.numeroCasa() != null)
            paciente.setNumeroCasa(dados.numeroCasa());

        return this.pacienteGateway.update(paciente);
    }

}
