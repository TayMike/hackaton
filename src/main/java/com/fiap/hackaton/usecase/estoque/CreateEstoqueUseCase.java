package com.fiap.hackaton.usecase.estoque;

import com.fiap.hackaton.entity.estoque.gateway.EstoqueGateway;
import com.fiap.hackaton.entity.estoque.model.Estoque;
import com.fiap.hackaton.usecase.estoque.dto.IEstoqueRegistrationData;

public class CreateEstoqueUseCase<T> {

    private final EstoqueGateway<T> estoqueGateway;

    public CreateEstoqueUseCase(EstoqueGateway<T> estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public Estoque<T> execute(IEstoqueRegistrationData<T> dados) {

        Estoque<T> estoque = new Estoque<T>(dados.itens(), dados.quantidades(), dados.hospital());

        return this.estoqueGateway.save(estoque);
    }

}
