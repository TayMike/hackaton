package com.fiap.hackaton.usecase.entregaInsumo.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;

import java.time.LocalDateTime;
import java.util.List;

public interface IEntregaInsumoRegistrationData {

    List<Insumo> insumo();

    List<Long> quantidade();

    Colaborador colaboradorRecebedor();

    LocalDateTime dataHoraRecebimento();

    Hospital hospital();

}
