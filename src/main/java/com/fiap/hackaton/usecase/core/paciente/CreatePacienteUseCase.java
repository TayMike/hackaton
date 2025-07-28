package com.fiap.hackaton.usecase.core.paciente;

import com.fiap.hackaton.entity.core.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import com.fiap.hackaton.usecase.core.paciente.dto.IPacienteRegistrationData;
import org.springframework.transaction.annotation.Transactional;

public class CreatePacienteUseCase {

    private final PacienteGateway pacienteGateway;

    public CreatePacienteUseCase(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    @Transactional
    public Paciente execute(IPacienteRegistrationData dados) {

        Paciente paciente = new Paciente(dados.cpf(), dados.nome(),
                dados.primeiroDiaCadastro(), dados.cep(), dados.numeroCasa());

        return this.pacienteGateway.save(paciente);
    }

}
