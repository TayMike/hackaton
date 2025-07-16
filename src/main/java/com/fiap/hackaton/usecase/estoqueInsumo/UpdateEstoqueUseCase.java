package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.usecase.estoqueInsumo.dto.IEstoqueInsumoUpdateData;

import java.util.UUID;

public class UpdateEstoqueUseCase {

    private final EstoqueInsumoGateway estoqueGateway;

    public UpdateEstoqueUseCase(EstoqueInsumoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    public EstoqueInsumo execute(UUID id, IEstoqueInsumoUpdateData dados) throws EstoqueInsumoNotFoundException {
        EstoqueInsumo estoqueInsumo = this.estoqueGateway.findById(id)
                .orElseThrow(EstoqueInsumoNotFoundException::new);

        if (dados.itens() != null && !dados.itens().isEmpty())
            estoqueInsumo.setItens(dados.itens());

        if (dados.quantidades() != null && !dados.quantidades().isEmpty())
            estoqueInsumo.setQuantidades(dados.quantidades());

        if (dados.hospital() != null)
            estoqueInsumo.setHospital(dados.hospital());

        return this.estoqueGateway.update(estoqueInsumo);
    }

}
