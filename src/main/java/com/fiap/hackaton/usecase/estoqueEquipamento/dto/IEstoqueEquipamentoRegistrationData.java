package com.fiap.hackaton.usecase.estoqueEquipamento.dto;

import java.util.List;
import java.util.UUID;

public interface IEstoqueEquipamentoRegistrationData {

    List<UUID> itens();

    List<Long> quantidades();

    UUID hospital();

}
