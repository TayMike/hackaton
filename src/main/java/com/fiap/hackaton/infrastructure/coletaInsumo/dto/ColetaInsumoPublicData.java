package com.fiap.hackaton.infrastructure.coletaInsumo.dto;

import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.coletaInsumo.dto.IColetaInsumoPublicData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ColetaInsumoPublicData(
        UUID id,
        List<Insumo> insumos,
        List<Long> quantidades,
        Colaborador colaboradorEntregador,
        LocalDateTime dataHoraColeta,
        Paciente pacienteRecebedor,
        Hospital hospital
) implements IColetaInsumoPublicData {

    public ColetaInsumoPublicData(ColetaInsumo coletaInsumo) {
        this(
                coletaInsumo.getId(),
                coletaInsumo.getInsumos(),
                coletaInsumo.getQuantidades(),
                coletaInsumo.getColaboradorEntregador(),
                coletaInsumo.getDataHoraColeta(),
                coletaInsumo.getPacienteRecebedor(),
                coletaInsumo.getHospital()
        );
    }
}