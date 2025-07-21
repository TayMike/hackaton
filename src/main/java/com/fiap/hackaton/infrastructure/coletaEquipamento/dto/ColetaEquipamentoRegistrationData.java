package com.fiap.hackaton.infrastructure.coletaEquipamento.dto;

import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.coletaEquipamento.dto.IColetaEquipamentoRegistrationData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record ColetaEquipamentoRegistrationData(
        List<UUID> equipamentos,
        List<Long> quantidades,
        UUID colaboradorEntregador,
        OffsetDateTime dataHoraColeta,
        UUID colaboradorResponsavel,
        UUID hospital
) implements IColetaEquipamentoRegistrationData {

    public ColetaEquipamentoRegistrationData(ColetaEquipamento coletaEquipamento) {
        this(  
                coletaEquipamento.getEquipamentos().stream()
                        .map(Equipamento::getId)
                        .toList(),
                coletaEquipamento.getQuantidades(),
                coletaEquipamento.getColaboradorEntregador().getId(),
                coletaEquipamento.getDataHoraColeta(),
                coletaEquipamento.getColaboradorResponsavel().getId(),
                coletaEquipamento.getHospital().getId());
    }
}
