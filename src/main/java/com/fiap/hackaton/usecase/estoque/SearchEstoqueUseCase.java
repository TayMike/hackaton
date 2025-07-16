package com.fiap.hackaton.usecase.estoque;

import com.fiap.hackaton.entity.estoque.gateway.EstoqueGateway;
import com.fiap.hackaton.entity.estoque.model.Estoque;

import java.util.List;

public class SearchEstoqueUseCase<T> {

    private final EstoqueGateway<T> estoqueGateway;

    public SearchEstoqueUseCase(EstoqueGateway<T> estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public List<Estoque<T>> execute() {
        return this.estoqueGateway.findAll();
    }

}
