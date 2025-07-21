package com.fiap.hackaton.infrastructure.entregaInsumo.dto;

import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.entregaInsumo.dto.IEntregaInsumoRegistrationData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record EntregaInsumoRegistrationData(
        List<UUID> insumo,
        List<Long> quantidade,
        UUID colaboradorRecebedor,
        OffsetDateTime dataHoraRecebimento,
        UUID hospital
) implements IEntregaInsumoRegistrationData {

    public EntregaInsumoRegistrationData(EntregaInsumo entregaInsumo) {
        this(
                entregaInsumo.getInsumo().stream().map(Insumo::getId).toList(),
                entregaInsumo.getQuantidade(),
                entregaInsumo.getColaboradorRecebedor().getId(),
                entregaInsumo.getDataHoraRecebimento(),
                entregaInsumo.getHospital().getId()
        );
    }
}