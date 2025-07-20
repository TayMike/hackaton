package com.fiap.hackaton.usecase.descarteEquipamento.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IDescarteEquipamentoPublicData {

    UUID id();

    List<Equipamento> equipamentos();

    List<Long> quantidade();

    Colaborador colaboradorResponsavel();

    LocalDateTime dataHoraDescarte();

    Hospital hospital();

}
