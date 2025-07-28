package com.fiap.hackaton.infrastructure.equipamentoDominio.entregaEquipamento.dto;

import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento.dto.IEntregaEquipamentoPublicData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EntregaEquipamentoPublicData (
        UUID id,
        UUID equipamentoId,
        UUID colaboradorRecebedorId,
        OffsetDateTime dataHoraRecebimento,
        UUID hospitalId
) implements IEntregaEquipamentoPublicData {

    public EntregaEquipamentoPublicData(EntregaEquipamento entregaEquipamento) {
        this(
                entregaEquipamento.getId(),
                entregaEquipamento.getEquipamentoId(),
                entregaEquipamento.getColaboradorRecebedorId(),
                entregaEquipamento.getDataHoraRecebimento(),
                entregaEquipamento.getHospitalId()
        );
    }
}
