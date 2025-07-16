package com.fiap.hackaton.infrastructure.coleta.dto;

import com.fiap.hackaton.entity.coleta.model.Coleta;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.coleta.dto.IColetaRegistrationData;

import java.time.LocalDateTime;
import java.util.List;

public record ColetaRegistrationData(
        List<Insumo> insumos,
        List<Long> quantidades,
        Colaborador colaboradorEntregador,
        LocalDateTime dataHoraColeta,
        Paciente pacienteRecebedor,
        Hospital hospital
) implements IColetaRegistrationData {

    public ColetaRegistrationData(Coleta coleta) {
        this(
                coleta.getInsumos(),
                coleta.getQuantidades(),
                coleta.getColaboradorEntregador(),
                coleta.getDataHoraColeta(),
                coleta.getPacienteRecebedor(),
                coleta.getHospital()
        );
    }
}
