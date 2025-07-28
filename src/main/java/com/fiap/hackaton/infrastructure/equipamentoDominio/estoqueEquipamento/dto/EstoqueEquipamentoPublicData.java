package com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.dto.IEstoqueEquipamentoPublicData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EstoqueEquipamentoPublicData(
        UUID id,
        UUID equipamentoId,
        UUID hospitalId,
        UUID colaboradorEntregadorId,
        OffsetDateTime dataHoraColeta,
        UUID colaboradorResponsavelId
) implements IEstoqueEquipamentoPublicData {

    public EstoqueEquipamentoPublicData(EstoqueEquipamento estoqueEquipamento) {
        this(
                estoqueEquipamento.getId(),
                estoqueEquipamento.getEquipamentoId(),
                estoqueEquipamento.getHospitalId(),
                estoqueEquipamento.getColaboradorEntregadorId(),
                estoqueEquipamento.getDataHoraColeta(),
                estoqueEquipamento.getColaboradorResponsavelId()
        );
    }
}
