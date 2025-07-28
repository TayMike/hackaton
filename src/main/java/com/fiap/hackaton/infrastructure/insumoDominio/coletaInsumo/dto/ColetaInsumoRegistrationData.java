package com.fiap.hackaton.infrastructure.insumoDominio.coletaInsumo.dto;

import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.usecase.insumoDominio.coletaInsumo.dto.IColetaInsumoRegistrationData;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public record ColetaInsumoRegistrationData(
        List<QuantidadeInsumo> insumos,
        UUID colaboradorEntregadorId,
        OffsetDateTime dataHoraColeta,
        UUID pacienteRecebedorId,
        UUID hospitalId
) implements IColetaInsumoRegistrationData {

    public ColetaInsumoRegistrationData(ColetaInsumo coletaInsumo) {
        this(
                coletaInsumo.getInsumos().stream().toList(),
                coletaInsumo.getColaboradorEntregadorId(),
                coletaInsumo.getDataHoraColeta(),
                coletaInsumo.getPacienteRecebedorId(),
                coletaInsumo.getHospitalId()
        );
    }
}
