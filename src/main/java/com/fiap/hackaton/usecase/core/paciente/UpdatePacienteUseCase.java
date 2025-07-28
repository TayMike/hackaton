package com.fiap.hackaton.usecase.core.paciente;

import com.fiap.hackaton.entity.core.paciente.exception.PacienteNotFoundException;
import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.usecase.core.paciente.dto.IPacienteUpdateData;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class UpdatePacienteUseCase {

    private final PacienteGateway pacienteGateway;

    public UpdatePacienteUseCase(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    @Transactional
    public Paciente execute(UUID id, IPacienteUpdateData dados) throws PacienteNotFoundException {
        Paciente paciente = this.pacienteGateway.findById(id)
                .orElseThrow(() -> new PacienteNotFoundException("Paciente not found: " + id));

        if (dados.nome() != null && !dados.nome().isBlank())
            paciente.setNome(dados.nome());

        if (dados.cep() != null && !dados.cep().isBlank())
            paciente.setCep(dados.cep());

        if (dados.numeroCasa() != null)
            paciente.setNumeroCasa(dados.numeroCasa());

        return this.pacienteGateway.update(paciente);
    }

}
