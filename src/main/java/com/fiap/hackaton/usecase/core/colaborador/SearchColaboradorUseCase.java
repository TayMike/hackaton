package com.fiap.hackaton.usecase.core.colaborador;

import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchColaboradorUseCase {

    private final ColaboradorGateway colaboradorGateway;

    public SearchColaboradorUseCase(ColaboradorGateway colaboradorGateway) {
        this.colaboradorGateway = colaboradorGateway;
    }

    @Transactional(readOnly = true)
    public List<Colaborador> execute() {
        return this.colaboradorGateway.findAll();
    }

}
