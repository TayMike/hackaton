package com.fiap.hackaton.infrastructure.estoqueInsumo.dto;

import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.estoqueInsumo.dto.IEstoqueInsumoPublicData;

import java.util.List;
import java.util.UUID;

public record EstoqueInsumoPublicData(
        UUID id,
        List<UUID> itens,
        List<Long> quantidades,
        UUID hospital
) implements IEstoqueInsumoPublicData {

    public EstoqueInsumoPublicData(EstoqueInsumo estoqueInsumo) {
        this(
                estoqueInsumo.getId(),
                estoqueInsumo.getItens().stream().map(Insumo::getId).toList(),
                estoqueInsumo.getQuantidades(),
                estoqueInsumo.getHospital().getId());
    }
}