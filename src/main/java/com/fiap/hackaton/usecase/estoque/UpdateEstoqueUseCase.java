package com.fiap.hackaton.usecase.estoque;

import com.fiap.hackaton.entity.estoque.exception.EstoqueNotFoundException;
import com.fiap.hackaton.entity.estoque.gateway.EstoqueGateway;
import com.fiap.hackaton.entity.estoque.model.Estoque;
import com.fiap.hackaton.usecase.estoque.dto.IEstoqueUpdateData;

import java.util.UUID;

public class UpdateEstoqueUseCase<T> {

    private final EstoqueGateway<T> estoqueGateway;

    public UpdateEstoqueUseCase(EstoqueGateway<T> estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public Estoque<T> execute(UUID id, IEstoqueUpdateData<T> dados) throws EstoqueNotFoundException {
        Estoque<T> estoque = this.estoqueGateway.findById(id)
                .orElseThrow(EstoqueNotFoundException::new);

        if (dados.itens() != null && !dados.itens().isEmpty())
            estoque.setItens(dados.itens());

        if (dados.quantidades() != null && !dados.quantidades().isEmpty())
            estoque.setQuantidades(dados.quantidades());

        if (dados.hospital() != null)
            estoque.setHospital(dados.hospital());

        return this.estoqueGateway.update(estoque);
    }

}
