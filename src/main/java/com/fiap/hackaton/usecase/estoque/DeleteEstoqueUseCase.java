package com.fiap.hackaton.usecase.estoque;

import com.fiap.hackaton.entity.estoque.exception.EstoqueNotFoundException;
import com.fiap.hackaton.entity.estoque.gateway.EstoqueGateway;
import com.fiap.hackaton.entity.estoque.model.Estoque;

import java.util.UUID;

public class DeleteEstoqueUseCase<T> {

    private final EstoqueGateway<T> estoqueGateway;

    public DeleteEstoqueUseCase(EstoqueGateway<T> estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public Estoque<T> execute(UUID id) throws EstoqueNotFoundException {
        Estoque<T> estoque = estoqueGateway.findById(id)
                .orElseThrow(EstoqueNotFoundException::new);

        estoqueGateway.deleteById(id);

        return estoque;
    }

}
