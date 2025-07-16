package com.fiap.hackaton.usecase.coleta.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.entity.paciente.model.Paciente;

import java.time.LocalDateTime;
import java.util.List;

public interface IColetaRegistrationData {

    List<Insumo> insumos();
    List<Long> quantidades();
    Colaborador colaboradorEntregador();
    LocalDateTime dataHoraColeta();
    Paciente pacienteRecebedor();
    Hospital hospital();

}