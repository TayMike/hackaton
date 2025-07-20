package com.fiap.hackaton.infrastructure.descarteEquipamento.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.descarteEquipamento.dto.IDescarteEquipamentoRegistrationData;

import java.time.LocalDateTime;
import java.util.List;

public record DescarteEquipamentoRegistrationData(
        List<Equipamento> equipamentos,
        List<Long> quantidade,
        Colaborador colaboradorResponsavel,
        LocalDateTime dataHoraDescarte,
        Hospital hospital
) implements IDescarteEquipamentoRegistrationData {

    public DescarteEquipamentoRegistrationData(DescarteEquipamento descarteEquipamento) {
        this(
                descarteEquipamento.getEquipamentos(),
                descarteEquipamento.getQuantidade(),
                descarteEquipamento.getColaboradorResponsavel(),
                descarteEquipamento.getDataHoraDescarte(),
                descarteEquipamento.getHospital()
        );
    }
}
