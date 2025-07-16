package com.fiap.hackaton.infrastructure.insumo.dto;

import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.insumo.dto.IInsumoPublicData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record InsumoPublicData(
        UUID id,
        String nome,
        BigDecimal custo,
        Long quantidade,
        Long peso,
        LocalDate validade,
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