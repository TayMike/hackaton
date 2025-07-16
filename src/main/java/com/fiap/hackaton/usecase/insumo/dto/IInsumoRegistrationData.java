package com.fiap.hackaton.usecase.insumo.dto;

import com.fiap.hackaton.entity.insumo.model.Insumo;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IInsumoRegistrationData {

    String nome();

    BigDecimal custo();

    Long quantidade();

    Long peso();

    LocalDate validade();

    String marca();

    Insumo.Medida unidadeMedida();

}