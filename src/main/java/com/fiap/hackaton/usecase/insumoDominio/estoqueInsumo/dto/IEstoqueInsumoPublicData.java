package com.fiap.hackaton.usecase.insumoDominio.estoqueInsumo.dto;

import com.fiap.hackaton.entity.insumoDominio.auxiliar.QuantidadeInsumo;

import java.util.List;
import java.util.UUID;

public interface IEstoqueInsumoPublicData {

    UUID id();

    List<QuantidadeInsumo> insumos();

    UUID hospitalId();

}
