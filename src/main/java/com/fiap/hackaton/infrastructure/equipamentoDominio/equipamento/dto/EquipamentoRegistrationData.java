package com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.dto;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.dto.IEquipamentoRegistrationData;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record EquipamentoRegistrationData(
        String nome,
        BigDecimal custo,
        OffsetDateTime tempoGarantia,
        OffsetDateTime proximaManutencaoPreventiva,
        String numeroSerie,
        String marca,
        OffsetDateTime descarte
) implements IEquipamentoRegistrationData {

    public EquipamentoRegistrationData(Equipamento equipamento) {
        this(
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
