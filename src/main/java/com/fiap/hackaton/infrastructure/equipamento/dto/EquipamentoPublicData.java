package com.fiap.hackaton.infrastructure.equipamento.dto;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamento.dto.IEquipamentoPublicData;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record EquipamentoPublicData(
        UUID id,
        String nome,
        BigDecimal custo,
        OffsetDateTime tempoGarantia,
        OffsetDateTime proximaManutencaoPreventiva,
        String marca,
        UUID hospital
) implements IEquipamentoPublicData {

    public EquipamentoPublicData(Equipamento equipamento) {
        this(
                equipamento.getId(),
                equipamento.getNome(),
                equipamento.getCusto(),
                equipamento.getTempoGarantia(),
                equipamento.getProximaManutencaoPreventiva(),
                equipamento.getMarca(),
                equipamento.getHospital().getId()
        );
    }
}