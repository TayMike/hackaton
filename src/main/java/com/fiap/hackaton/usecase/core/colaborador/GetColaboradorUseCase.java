package com.fiap.hackaton.usecase.core.colaborador;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetColaboradorUseCase {

    private final ColaboradorGateway colaboradorGateway;

    public GetColaboradorUseCase(ColaboradorGateway colaboradorGateway) {
        this.colaboradorGateway = colaboradorGateway;
    }

    @Transactional(readOnly = true)
    public Colaborador execute(UUID id) throws ColaboradorNotFoundException {
        return this.colaboradorGateway.findById(id).
                orElseThrow(() -> new ColaboradorNotFoundException("Colaborador not found: " + id));
    }

}
