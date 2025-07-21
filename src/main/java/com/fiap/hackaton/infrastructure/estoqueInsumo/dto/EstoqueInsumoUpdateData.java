package com.fiap.hackaton.infrastructure.estoqueInsumo.dto;

import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.estoqueInsumo.dto.IEstoqueInsumoUpdateData;

import java.util.List;
import java.util.UUID;

public record EstoqueInsumoUpdateData(
        List<UUID> itens,
        List<Long> quantidades,
        UUID hospital
) implements IEstoqueInsumoUpdateData {

    public EstoqueInsumoUpdateData(EstoqueInsumo estoqueInsumo) {
        this(
                estoqueInsumo.getItens().stream().map(Insumo::getId).toList(),
                estoqueInsumo.getQuantidades(),
                estoqueInsumo.getHospital().getId()
        );
    }
}