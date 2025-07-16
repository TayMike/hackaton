package com.fiap.hackaton.infrastructure.entrega.dto;

import com.fiap.hackaton.entity.entrega.model.Entrega;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.entrega.dto.IEntregaPublicData;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record EntregaPublicData(
        UUID id,
        List<Insumo> insumo,
        List<Long> quantidade,
        Colaborador colaboradorRecebedor,
        LocalDateTime dataHoraRecebimento,
        Hospital hospital
) implements IEntregaPublicData {

    public EntregaPublicData(Entrega entrega) {
        this(
                entrega.getId(),
                entrega.getInsumo(),
                entrega.getQuantidade(),
                entrega.getColaboradorRecebedor(),
                entrega.getDataHoraRecebimento(),
                entrega.getHospital()
        );
    }
}