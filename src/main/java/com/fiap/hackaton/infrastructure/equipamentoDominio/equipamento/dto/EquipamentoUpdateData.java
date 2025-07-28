package com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.dto;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.dto.IEquipamentoUpdateData;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record EquipamentoUpdateData(
        String nome,
        BigDecimal custo,
        OffsetDateTime tempoGarantia,
        OffsetDateTime proximaManutencaoPreventiva,
        String marca,
        OffsetDateTime descarte
) implements IEquipamentoUpdateData {

    public EquipamentoUpdateData(Equipamento equipamento) {
        this(
                equipamento.getNome(),
                equipamento.getCusto(),
                equipamento.getTempoGarantia(),
                equipamento.getProximaManutencaoPreventiva(),
                equipamento.getMarca(),
                equipamento.getDescarte()
        );
    }
}