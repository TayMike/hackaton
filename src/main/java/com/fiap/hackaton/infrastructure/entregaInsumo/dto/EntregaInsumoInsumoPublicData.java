package com.fiap.hackaton.infrastructure.entregaInsumo.dto;

import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.entregaInsumo.dto.IEntregaInsumoPublicData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EntregaInsumoInsumoPublicData(
        UUID id,
        List<Insumo> insumo,
        List<Long> quantidade,
        Colaborador colaboradorRecebedor,
        LocalDateTime dataHoraRecebimento,
        Hospital hospital
) implements IEntregaInsumoPublicData {

    public EntregaInsumoInsumoPublicData(EntregaInsumo entregaInsumo) {
        this(
                entregaInsumo.getId(),
                entregaInsumo.getInsumo(),
                entregaInsumo.getQuantidade(),
                entregaInsumo.getColaboradorRecebedor(),
                entregaInsumo.getDataHoraRecebimento(),
                entregaInsumo.getHospital()
        );
    }
}