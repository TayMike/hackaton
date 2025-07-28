package com.fiap.hackaton.infrastructure.insumoDominio.estoqueInsumo.dto;

import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.usecase.insumoDominio.estoqueInsumo.dto.IEstoqueInsumoPublicData;

import java.util.List;
import java.util.UUID;

public record EstoqueInsumoPublicData(
        UUID id,
        List<QuantidadeInsumo> insumos,
        UUID hospitalId
) implements IEstoqueInsumoPublicData {

    public EstoqueInsumoPublicData(EstoqueInsumo estoqueInsumo) {
        this(
                estoqueInsumo.getId(),
                estoqueInsumo.getInsumos(),
                estoqueInsumo.getHospitalId());
    }
}