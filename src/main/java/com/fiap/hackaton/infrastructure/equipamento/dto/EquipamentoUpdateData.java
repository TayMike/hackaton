package com.fiap.hackaton.infrastructure.equipamento.dto;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.equipamento.dto.IEquipamentoUpdateData;
import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EquipamentoUpdateData(
        String nome,
        BigDecimal custo,
        LocalDate tempoGarantia,
        LocalDate proximaManutencaoPreventiva,
        String marca,
        Hospital hospital
) implements IEquipamentoUpdateData {

    public EquipamentoUpdateData(Equipamento equipamento) {
        this(
                equipamento.getNome(),
                equipamento.getCusto(),
                equipamento.getTempoGarantia(),
                equipamento.getProximaManutencaoPreventiva(),
                equipamento.getMarca(),
                equipamento.getHospital()
        );
    }
}