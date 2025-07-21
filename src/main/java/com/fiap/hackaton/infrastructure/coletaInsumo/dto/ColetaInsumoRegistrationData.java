package com.fiap.hackaton.infrastructure.coletaInsumo.dto;

import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.entity.paciente.model.Paciente;
import com.fiap.hackaton.usecase.coletaInsumo.dto.IColetaInsumoRegistrationData;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record ColetaInsumoRegistrationData(
        List<UUID> insumos,
        List<Long> quantidades,
        UUID colaboradorEntregador,
        OffsetDateTime dataHoraColeta,
        UUID pacienteRecebedor,
        UUID hospital
) implements IColetaInsumoRegistrationData {

    public ColetaInsumoRegistrationData(ColetaInsumo coletaInsumo) {
        this(
                coletaInsumo.getInsumos().stream().map(Insumo::getId).toList(),
                coletaInsumo.getQuantidades(),
                coletaInsumo.getColaboradorEntregador().getId(),
                coletaInsumo.getDataHoraColeta(),
                coletaInsumo.getPacienteRecebedor().getId(),
                coletaInsumo.getHospital().getId()
        );
    }
}
