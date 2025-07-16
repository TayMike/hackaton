package com.fiap.hackaton.infrastructure.entrega.dto;

import com.fiap.hackaton.entity.entrega.model.Entrega;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.usecase.entrega.dto.IEntregaRegistrationData;

import java.time.LocalDateTime;
import java.util.List;

public record EntregaRegistrationData(
        List<Insumo> insumo,
        List<Long> quantidade,
        Colaborador colaboradorRecebedor,
        LocalDateTime dataHoraRecebimento,
        Hospital hospital
) implements IEntregaRegistrationData {

    public EntregaRegistrationData(Entrega entrega) {
        this(
                entrega.getInsumo(),
                entrega.getQuantidade(),
                entrega.getColaboradorRecebedor(),
                entrega.getDataHoraRecebimento(),
                entrega.getHospital()
        );
    }
}