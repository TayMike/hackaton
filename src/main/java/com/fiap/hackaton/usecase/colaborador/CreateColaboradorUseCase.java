package com.fiap.hackaton.usecase.colaborador;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.usecase.colaborador.dto.IColaboradorRegistrationData;

public class CreateColaboradorUseCase {

    private final ColaboradorGateway colaboradorGateway;

    public CreateColaboradorUseCase(ColaboradorGateway colaboradorGateway) {
        this.colaboradorGateway = colaboradorGateway;
    }

    public Colaborador execute(IColaboradorRegistrationData dados) {

        Colaborador colaborador = new Colaborador(dados.cpf(), dados.nome(), dados.matricula(),
                dados.primeiroDiaCadastro(), dados.cep(), dados.numeroCasa(), dados.hospital(), dados.setor(), dados.ativo());

        return this.colaboradorGateway.save(colaborador);
    }

}