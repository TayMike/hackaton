package com.fiap.hackaton.infrastructure.entregaInsumo.dto;

import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.entregaInsumo.dto.IEntregaInsumoRegistrationData;

import java.time.LocalDateTime;
import java.util.List;

public record EntregaInsumoRegistrationData(
        List<Insumo> insumo,
        List<Long> quantidade,
        Colaborador colaboradorRecebedor,
        LocalDateTime dataHoraRecebimento,
        Hospital hospital
) implements IEntregaInsumoRegistrationData {

    public EntregaInsumoRegistrationData(EntregaInsumo entregaInsumo) {
        this(
                entregaInsumo.getInsumo(),
                entregaInsumo.getQuantidade(),
                entregaInsumo.getColaboradorRecebedor(),
                entregaInsumo.getDataHoraRecebimento(),
                entregaInsumo.getHospital()
        );
    }
}