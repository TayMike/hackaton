package com.fiap.hackaton.usecase.equipamento.dto;

import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface IEquipamentoPublicData {

    UUID id();

    String nome();

    BigDecimal custo();

    LocalDate tempoGarantia();

    LocalDate proximaManutencaoPreventiva();

    String marca();

    Hospital hospital();

}
