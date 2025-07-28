package com.fiap.hackaton.usecase.insumoDominio.coletaInsumo.dto;

import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface IColetaInsumoRegistrationData {

    List<QuantidadeInsumo> insumos();

    UUID colaboradorEntregadorId();

    OffsetDateTime dataHoraColeta();

    UUID pacienteRecebedorId();

    UUID hospitalId();

}