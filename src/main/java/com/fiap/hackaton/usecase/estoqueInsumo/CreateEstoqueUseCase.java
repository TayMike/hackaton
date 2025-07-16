package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.usecase.estoqueInsumo.dto.IEstoqueInsumoRegistrationData;

public class CreateEstoqueUseCase {

    private final EstoqueInsumoGateway estoqueGateway;

    public CreateEstoqueUseCase(EstoqueInsumoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public EstoqueInsumo execute(IEstoqueInsumoRegistrationData dados) {

        EstoqueInsumo estoqueInsumo = new EstoqueInsumo(dados.itens(), dados.quantidades(), dados.hospital());

        return this.estoqueGateway.save(estoqueInsumo);
    }

}
