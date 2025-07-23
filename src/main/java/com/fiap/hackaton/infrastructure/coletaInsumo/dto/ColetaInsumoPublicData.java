package com.fiap.hackaton.infrastructure.coletaInsumo.dto;

import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.coletaInsumo.dto.IColetaInsumoPublicData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record ColetaInsumoPublicData(
        UUID id,
        List<UUID> insumos,
        List<Long> quantidades,
        UUID colaboradorEntregador,
        OffsetDateTime dataHoraColeta,
        UUID pacienteRecebedor,
        UUID hospital
) implements IColetaInsumoPublicData {

    public ColetaInsumoPublicData(ColetaInsumo coletaInsumo) {
        this(
                coletaInsumo.getId(),
                coletaInsumo.getInsumos().stream().map(Insumo::getId).toList(),
                coletaInsumo.getQuantidades(),
                coletaInsumo.getColaboradorEntregador().getId(),
                coletaInsumo.getDataHoraColeta(),
                coletaInsumo.getPacienteRecebedor().getId(),
                coletaInsumo.getHospital().getId()
        );
    }
}