package com.fiap.hackaton.infrastructure.entregaEquipamento.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.entregaEquipamento.dto.IEntregaEquipamentoPublicData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EntregaEquipamentoPublicData (
        UUID id,
        List<Equipamento> equipamentos,
        List<Long> quantidade,
        Colaborador colaboradorRecebedor,
        LocalDateTime dataHoraRecebimento,
        Hospital hospital
) implements IEntregaEquipamentoPublicData {

    public EntregaEquipamentoPublicData(EntregaEquipamento entregaEquipamento) {
        this(
                entregaEquipamento.getId(),
                entregaEquipamento.getEquipamentos(),
                entregaEquipamento.getQuantidade(),
                entregaEquipamento.getColaboradorRecebedor(),
                entregaEquipamento.getDataHoraRecebimento(),
                entregaEquipamento.getHospital()
        );
    }
}
