package com.fiap.hackaton.usecase.estoqueInsumo.dto;

import java.util.List;
import java.util.UUID;

public interface IEstoqueInsumoRegistrationData {

    List<UUID> itens();

    List<Long> quantidades();

    UUID hospital();

}