package com.fiap.hackaton.infrastructure.insumoDominio.entregaInsumo.dto;

import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.usecase.insumoDominio.entregaInsumo.dto.IEntregaInsumoPublicData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record EntregaInsumoPublicData(
        UUID id,
        List<QuantidadeInsumo> insumos,
        UUID colaboradorRecebedorId,
        OffsetDateTime dataHoraRecebimento,
        UUID hospitalId
) implements IEntregaInsumoPublicData {

    public EntregaInsumoPublicData(EntregaInsumo entregaInsumo) {
        this(
                entregaInsumo.getId(),
                entregaInsumo.getInsumos(),
                entregaInsumo.getColaboradorRecebedorId(),
                entregaInsumo.getDataHoraRecebimento(),
                entregaInsumo.getHospitalId()
        );
    }
}