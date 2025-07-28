package com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.dto;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.dto.IEquipamentoPublicData;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record EquipamentoPublicData(
        UUID id,
        String nome,
        BigDecimal custo,
        OffsetDateTime tempoGarantia,
        OffsetDateTime proximaManutencaoPreventiva,
        String numeroSerie,
        String marca,
        OffsetDateTime descarte
) implements IEquipamentoPublicData {

    public EquipamentoPublicData(Equipamento equipamento) {
        this(
                equipamento.getId(),
                equipamento.getNome(),
                equipamento.getCusto(),
                equipamento.getTempoGarantia(),
                equipamento.getProximaManutencaoPreventiva(),
                equipamento.getNumeroSerie(),
                equipamento.getMarca(),
                equipamento.getDescarte()
        );
    }
}