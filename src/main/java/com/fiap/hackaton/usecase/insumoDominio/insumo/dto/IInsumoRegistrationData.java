package com.fiap.hackaton.usecase.insumoDominio.insumo.dto;

import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface IInsumoRegistrationData {

    String nome();

    BigDecimal custo();

    Long quantidade();

    Long peso();

    OffsetDateTime validade();

    String marca();

    Insumo.Medida unidadeMedida();

}