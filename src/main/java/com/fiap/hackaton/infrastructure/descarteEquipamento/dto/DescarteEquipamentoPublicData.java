package com.fiap.hackaton.infrastructure.descarteEquipamento.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.descarteEquipamento.dto.IDescarteEquipamentoPublicData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record DescarteEquipamentoPublicData (
        UUID id,
        List<Equipamento> equipamentos,
        List<Long> quantidade,
        Colaborador colaboradorResponsavel,
        LocalDateTime dataHoraDescarte,
        Hospital hospital
) implements IDescarteEquipamentoPublicData {

    public DescarteEquipamentoPublicData(DescarteEquipamento descarteEquipamento) {
        this(
                descarteEquipamento.getId(),
                descarteEquipamento.getEquipamentos(),
                descarteEquipamento.getQuantidade(),
                descarteEquipamento.getColaboradorResponsavel(),
                descarteEquipamento.getDataHoraDescarte(),
                descarteEquipamento.getHospital()
        );
    }
}
