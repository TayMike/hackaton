package com.fiap.hackaton.infrastructure.insumoDominio.insumo.dto;

import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import com.fiap.hackaton.usecase.insumoDominio.insumo.dto.IInsumoUpdateData;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record InsumoUpdateData(
        String nome,
        BigDecimal custo,
        Long quantidade,
        Long peso,
        OffsetDateTime validade,
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