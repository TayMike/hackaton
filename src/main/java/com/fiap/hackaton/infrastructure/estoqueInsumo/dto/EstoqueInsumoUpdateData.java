package com.fiap.hackaton.infrastructure.estoqueInsumo.dto;

import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.estoqueInsumo.dto.IEstoqueInsumoUpdateData;

import java.util.List;

public record EstoqueInsumoUpdateData(
        List<Insumo> itens,
        List<Long> quantidades,
        Hospital hospital
) implements IEstoqueInsumoUpdateData {

    public EstoqueInsumoUpdateData(EstoqueInsumo estoqueInsumo) {
        this(
                estoqueInsumo.getItens(),
                estoqueInsumo.getQuantidades(),
                estoqueInsumo.getHospital()
        );
    }
}