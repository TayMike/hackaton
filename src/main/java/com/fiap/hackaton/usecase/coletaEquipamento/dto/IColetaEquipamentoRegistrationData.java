package com.fiap.hackaton.usecase.coletaEquipamento.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.time.LocalDateTime;
import java.util.List;

public interface IColetaEquipamentoRegistrationData {

    List<Equipamento> equipamentos();

    List<Long> quantidades();

    Colaborador colaboradorEntregador();

    LocalDateTime dataHoraColeta();

    Colaborador colaboradorResponsavel();

    Hospital hospital();

}
