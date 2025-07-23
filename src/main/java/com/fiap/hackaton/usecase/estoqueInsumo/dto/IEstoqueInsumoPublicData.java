package com.fiap.hackaton.usecase.estoqueInsumo.dto;

import java.util.List;
import java.util.UUID;

public interface IEstoqueInsumoPublicData {

    UUID id();

    List<UUID> itens();

    List<Long> quantidades();

    UUID hospital();

}
