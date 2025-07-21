package com.fiap.hackaton.infrastructure.entregaInsumo.dto;

import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.entregaInsumo.dto.IEntregaInsumoPublicData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record EntregaInsumoPublicData(
        UUID id,
        List<UUID> insumo,
        List<Long> quantidade,
        UUID colaboradorRecebedor,
        OffsetDateTime dataHoraRecebimento,
        UUID hospital
) implements IEntregaInsumoPublicData {

    public EntregaInsumoPublicData(EntregaInsumo entregaInsumo) {
        this(
                entregaInsumo.getId(),
                entregaInsumo.getInsumo().stream()
                        .map(Insumo::getId)
                        .toList(),
                entregaInsumo.getQuantidade(),
                entregaInsumo.getColaboradorRecebedor().getId(),
                entregaInsumo.getDataHoraRecebimento(),
                entregaInsumo.getHospital().getId()
        );
    }
}