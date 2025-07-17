package com.fiap.hackaton.infrastructure.coletaInsumo.dto;

import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.coletaInsumo.dto.IColetaInsumoRegistrationData;

import java.time.LocalDateTime;
import java.util.List;

public record ColetaInsumoRegistrationData(
        List<Insumo> insumos,
        List<Long> quantidades,
        Colaborador colaboradorEntregador,
        LocalDateTime dataHoraColeta,
        Paciente pacienteRecebedor,
        Hospital hospital
) implements IColetaInsumoRegistrationData {

    public ColetaInsumoRegistrationData(ColetaInsumo coletaInsumo) {
        this(
                coletaInsumo.getInsumos(),
                coletaInsumo.getQuantidades(),
                coletaInsumo.getColaboradorEntregador(),
                coletaInsumo.getDataHoraColeta(),
                coletaInsumo.getPacienteRecebedor(),
                coletaInsumo.getHospital()
        );
    }
}
