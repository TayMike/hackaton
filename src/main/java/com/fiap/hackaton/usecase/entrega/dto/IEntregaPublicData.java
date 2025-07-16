package com.fiap.hackaton.usecase.entrega.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IEntregaPublicData {

    UUID id();
    List<Insumo> insumo();
    List<Long> quantidade();
    Colaborador colaboradorRecebedor();
    LocalDateTime dataHoraRecebimento();
    Hospital hospital();


}
