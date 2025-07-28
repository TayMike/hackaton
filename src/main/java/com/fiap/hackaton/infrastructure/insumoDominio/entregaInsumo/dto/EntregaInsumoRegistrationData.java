package com.fiap.hackaton.infrastructure.insumoDominio.entregaInsumo.dto;

import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.usecase.insumoDominio.entregaInsumo.dto.IEntregaInsumoRegistrationData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record EntregaInsumoRegistrationData(
        List<QuantidadeInsumo> insumos,
        UUID colaboradorRecebedorId,
        OffsetDateTime dataHoraRecebimento,
        UUID hospitalId
) implements IEntregaInsumoRegistrationData {

    public EntregaInsumoRegistrationData(EntregaInsumo entregaInsumo) {
        this(
                entregaInsumo.getInsumos().stream().toList(),
                entregaInsumo.getColaboradorRecebedorId(),
                entregaInsumo.getDataHoraRecebimento(),
                entregaInsumo.getHospitalId()
        );
    }
}