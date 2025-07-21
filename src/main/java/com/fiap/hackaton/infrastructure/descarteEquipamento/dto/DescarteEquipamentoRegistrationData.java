package com.fiap.hackaton.infrastructure.descarteEquipamento.dto;

import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.descarteEquipamento.dto.IDescarteEquipamentoRegistrationData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record DescarteEquipamentoRegistrationData(
        List<UUID> equipamentos,
        List<Long> quantidade,
        UUID colaboradorResponsavel,
        OffsetDateTime dataHoraDescarte,
        UUID hospital
) implements IDescarteEquipamentoRegistrationData {

    public DescarteEquipamentoRegistrationData(DescarteEquipamento descarteEquipamento) {
        this(
                descarteEquipamento.getEquipamentos().stream().map(Equipamento::getId).toList(),
                descarteEquipamento.getQuantidade(),
                descarteEquipamento.getColaboradorResponsavel().getId(),
                descarteEquipamento.getDataHoraDescarte(),
                descarteEquipamento.getHospital().getId()
        );
    }
}
