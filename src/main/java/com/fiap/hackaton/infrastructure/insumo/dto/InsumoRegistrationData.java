package com.fiap.hackaton.infrastructure.insumo.dto;

import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.insumo.dto.IInsumoRegistrationData;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InsumoRegistrationData(
        String nome,
        BigDecimal custo,
        Long quantidade,
        Long peso,
        LocalDate validade,
        String marca,
        Insumo.Medida unidadeMedida
) implements IInsumoRegistrationData {

    public InsumoRegistrationData(Insumo insumo) {
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