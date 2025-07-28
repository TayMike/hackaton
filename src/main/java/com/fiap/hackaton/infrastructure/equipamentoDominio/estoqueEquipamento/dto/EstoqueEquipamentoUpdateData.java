package com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.dto.IEstoqueEquipamentoUpdateData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EstoqueEquipamentoUpdateData(
        UUID colaboradorEntregadorId,
        OffsetDateTime dataHoraColeta,
        UUID colaboradorResponsavelId
) implements IEstoqueEquipamentoUpdateData {
    public EstoqueEquipamentoUpdateData(EstoqueEquipamento estoqueEquipamento) {
        this(
                estoqueEquipamento.getColaboradorEntregadorId(),
                estoqueEquipamento.getDataHoraColeta(),
                estoqueEquipamento.getColaboradorResponsavelId()
        );
    }
}