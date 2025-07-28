package com.fiap.hackaton.infrastructure.equipamentoDominio.entregaEquipamento.dto;

import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento.dto.IEntregaEquipamentoRegistrationData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EntregaEquipamentoRegistrationData(
        UUID equipamentoId,
        UUID colaboradorRecebedorId,
        OffsetDateTime dataHoraRecebimento,
        UUID hospitalId
) implements IEntregaEquipamentoRegistrationData {

    public EntregaEquipamentoRegistrationData(EntregaEquipamento entregaEquipamento) {
        this(
                entregaEquipamento.getEquipamentoId(),
                entregaEquipamento.getColaboradorRecebedorId(),
                entregaEquipamento.getDataHoraRecebimento(),
                entregaEquipamento.getHospitalId()
        );
    }
}
