package com.fiap.hackaton.usecase.coletaInsumo.dto;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.entity.paciente.model.Paciente;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface IColetaInsumoPublicData {

    UUID id();

    List<Insumo> insumos();

    List<Long> quantidades();

    UUID colaboradorEntregador();

    OffsetDateTime dataHoraColeta();

    UUID pacienteRecebedor();

    Hospital hospital();

}
