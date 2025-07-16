package com.fiap.hackaton.infrastructure.estoqueInsumo.dto;

import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.estoqueInsumo.dto.IEstoqueInsumoPublicData;

import java.util.List;
import java.util.UUID;

public record EstoqueInsumoPublicData(
        UUID id,
        List<Insumo> itens,
        List<Long> quantidades,
        Hospital hospital
) implements IEstoqueInsumoPublicData {

    public EstoqueInsumoPublicData(EstoqueInsumo estoqueInsumo) {
        this(
                estoqueInsumo.getId(),
                estoqueInsumo.getItens(),
                estoqueInsumo.getQuantidades(),
                estoqueInsumo.getHospital()
        );
    }
}