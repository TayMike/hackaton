package com.fiap.hackaton.infrastructure.insumo.dto;

import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.insumo.dto.IInsumoUpdateData;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InsumoUpdateData(
        String nome,
        BigDecimal custo,
        Long quantidade,
        Long peso,
        LocalDate validade,
        String marca,
        Insumo.Medida unidadeMedida
) implements IInsumoUpdateData {

    public InsumoUpdateData(Insumo insumo) {
        this(
                insumo.getNome(),
                insumo.getCusto(),
                insumo.getQuantidade(),
                insumo.getPeso(),
                insumo.getValidade(),
                insumo.getMarca(),
                insumo.getUnidadeMedida()
        );
    }
}