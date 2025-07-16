package com.fiap.hackaton.usecase.paciente;

import com.fiap.hackaton.entity.paciente.gateway.PacienteGateway;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.paciente.dto.IPacienteRegistrationData;

public class CreatePacienteUseCase {

    private final PacienteGateway pacienteGateway;

    public CreatePacienteUseCase(PacienteGateway pacienteGateway) {
        this.pacienteGateway = pacienteGateway;
    }

    public Paciente execute(IPacienteRegistrationData dados) {

        Paciente paciente = new Paciente(dados.cpf(), dados.nome(),
                dados.primeiroDiaCadastro(), dados.cep(), dados.numeroCasa());

        return this.pacienteGateway.save(paciente);
    }

}
