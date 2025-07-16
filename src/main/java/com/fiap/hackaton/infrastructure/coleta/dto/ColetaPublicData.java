package com.fiap.hackaton.infrastructure.coleta.dto;

import com.fiap.hackaton.entity.coleta.model.Coleta;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.coleta.dto.IColetaPublicData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ColetaPublicData(
        UUID id,
        List<Insumo> insumos,
        List<Long> quantidades,
        Colaborador colaboradorEntregador,
        LocalDateTime dataHoraColeta,
        Paciente pacienteRecebedor,
        Hospital hospital
) implements IColetaPublicData {

    public ColetaPublicData(Coleta coleta) {
        this(
                coleta.getId(),
                coleta.getInsumos(),
                coleta.getQuantidades(),
                coleta.getColaboradorEntregador(),
                coleta.getDataHoraColeta(),
                coleta.getPacienteRecebedor(),
                coleta.getHospital()
        );
    }
}