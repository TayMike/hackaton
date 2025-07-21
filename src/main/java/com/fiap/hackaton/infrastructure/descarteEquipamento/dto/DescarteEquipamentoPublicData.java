package com.fiap.hackaton.infrastructure.descarteEquipamento.dto;

import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.descarteEquipamento.dto.IDescarteEquipamentoPublicData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record DescarteEquipamentoPublicData (
        UUID id,
        List<UUID> equipamentos,
        List<Long> quantidade,
        UUID colaboradorResponsavel,
        OffsetDateTime dataHoraDescarte,
        UUID hospital
) implements IDescarteEquipamentoPublicData {

    public DescarteEquipamentoPublicData(DescarteEquipamento descarteEquipamento) {
        this(
                descarteEquipamento.getId(),
                descarteEquipamento.getEquipamentos().stream().map(Equipamento::getId).toList(),
                descarteEquipamento.getQuantidade(),
                descarteEquipamento.getColaboradorResponsavel().getId(),
                descarteEquipamento.getDataHoraDescarte(),
                descarteEquipamento.getHospital().getId()
        );
    }
}
