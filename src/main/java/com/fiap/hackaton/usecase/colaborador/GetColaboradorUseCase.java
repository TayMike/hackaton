package com.fiap.hackaton.usecase.colaborador;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;

import java.util.UUID;

public class GetColaboradorUseCase {

    private final ColaboradorGateway colaboradorGateway;

    public GetColaboradorUseCase(ColaboradorGateway colaboradorGateway) {
        this.colaboradorGateway = colaboradorGateway;
    }

    public Colaborador execute(UUID id) throws ColaboradorNotFoundException {
        return this.colaboradorGateway.findById(id).
                orElseThrow(ColaboradorNotFoundException::new);
    }

}
