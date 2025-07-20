package com.fiap.hackaton.usecase.descarteEquipamento.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.time.LocalDateTime;
import java.util.List;

public interface IDescarteEquipamentoRegistrationData {

    List<Equipamento> equipamentos();

    List<Long> quantidade();

    Colaborador colaboradorResponsavel();

    LocalDateTime dataHoraDescarte();

    Hospital hospital();

}
