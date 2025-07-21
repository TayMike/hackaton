package com.fiap.hackaton.infrastructure.entregaEquipamento.dto;

import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.usecase.entregaEquipamento.dto.IEntregaEquipamentoRegistrationData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EntregaEquipamentoRegistrationData(
        List<UUID> equipamentos,
        List<Long> quantidade,
        UUID colaboradorRecebedor,
        LocalDateTime dataHoraRecebimento,
        UUID hospital
) implements IEntregaEquipamentoRegistrationData {

    public EntregaEquipamentoRegistrationData(EntregaEquipamento entregaEquipamento) {
        this(
                entregaEquipamento.getEquipamentos().stream().map(Equipamento::getId).toList(),
                entregaEquipamento.getQuantidade(),
                entregaEquipamento.getColaboradorRecebedor().getId(),
                entregaEquipamento.getDataHoraRecebimento(),
                entregaEquipamento.getHospital().getId()
        );
    }
}
