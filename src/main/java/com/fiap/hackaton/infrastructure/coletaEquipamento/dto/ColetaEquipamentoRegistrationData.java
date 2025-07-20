package com.fiap.hackaton.infrastructure.coletaEquipamento.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.usecase.coletaEquipamento.dto.IColetaEquipamentoRegistrationData;

import java.time.LocalDateTime;
import java.util.List;

public record ColetaEquipamentoRegistrationData(
        List<Equipamento> equipamentos,
        List<Long> quantidades,
        Colaborador colaboradorEntregador,
        LocalDateTime dataHoraColeta,
        Colaborador colaboradorResponsavel,
        Hospital hospital
) implements IColetaEquipamentoRegistrationData {

    public ColetaEquipamentoRegistrationData(ColetaEquipamento coletaEquipamento) {
        this(
                coletaEquipamento.getEquipamentos(),
                coletaEquipamento.getQuantidades(),
                coletaEquipamento.getColaboradorEntregador(),
                coletaEquipamento.getDataHoraColeta(),
                coletaEquipamento.getColaboradorResponsavel(),
                coletaEquipamento.getHospital()
        );
    }
}
