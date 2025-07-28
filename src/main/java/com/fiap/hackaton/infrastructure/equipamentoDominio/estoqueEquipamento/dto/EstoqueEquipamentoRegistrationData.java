package com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.dto;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.dto.IEstoqueEquipamentoRegistrationData;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EstoqueEquipamentoRegistrationData (
        UUID equipamentoId,
        UUID hospitalId,
        UUID colaboradorEntregadorId,
        OffsetDateTime dataHoraColeta,
        UUID colaboradorResponsavelId
) implements IEstoqueEquipamentoRegistrationData {

    public EstoqueEquipamentoRegistrationData(EstoqueEquipamento estoqueEquipamento) {
        this(
                estoqueEquipamento.getEquipamentoId(),
                estoqueEquipamento.getHospitalId(),
                estoqueEquipamento.getColaboradorEntregadorId(),
                estoqueEquipamento.getDataHoraColeta(),
                estoqueEquipamento.getColaboradorResponsavelId()
        );
    }
}
