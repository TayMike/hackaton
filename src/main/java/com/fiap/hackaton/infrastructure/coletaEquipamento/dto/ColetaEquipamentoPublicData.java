package com.fiap.hackaton.infrastructure.coletaEquipamento.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.coletaEquipamento.dto.IColetaEquipamentoPublicData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ColetaEquipamentoPublicData(
        UUID id,
        List<Equipamento> equipamentos,
        List<Long> quantidades,
        Colaborador colaboradorEntregador,
        LocalDateTime dataHoraColeta,
        Colaborador colaboradorResponsavel,
        Hospital hospital
) implements IColetaEquipamentoPublicData {

    public ColetaEquipamentoPublicData(ColetaEquipamento coletaEquipamento) {
        this(
                coletaEquipamento.getId(),
                coletaEquipamento.getEquipamentos(),
                coletaEquipamento.getQuantidades(),
                coletaEquipamento.getColaboradorEntregador(),
                coletaEquipamento.getDataHoraColeta(),
                coletaEquipamento.getColaboradorResponsavel(),
                coletaEquipamento.getHospital()
        );
    }
}
