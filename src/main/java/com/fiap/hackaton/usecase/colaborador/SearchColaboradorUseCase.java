package com.fiap.hackaton.usecase.colaborador;

import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;

import java.util.List;

public class SearchColaboradorUseCase {

    private final ColaboradorGateway colaboradorGateway;

    public SearchColaboradorUseCase(ColaboradorGateway colaboradorGateway) {
        this.colaboradorGateway = colaboradorGateway;
    }

    public List<Colaborador> execute() {
        return this.colaboradorGateway.findAll().stream().
                map(ColaboradorSchema::toColaborador)
                .toList();
    }

}
