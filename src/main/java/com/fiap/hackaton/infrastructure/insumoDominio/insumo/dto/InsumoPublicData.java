package com.fiap.hackaton.infrastructure.insumoDominio.insumo.dto;

import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import com.fiap.hackaton.usecase.insumoDominio.insumo.dto.IInsumoPublicData;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record InsumoPublicData(
        UUID id,
        String nome,
        BigDecimal custo,
        Long quantidade,
        Long peso,
        OffsetDateTime validade,
        String marca,
        Insumo.Medida unidadeMedida
) implements IInsumoPublicData {

    public InsumoPublicData(Insumo insumo) {
        this(
                insumo.getId(),
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