package com.fiap.hackaton.usecase.equipamento;

import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;

import java.util.List;

public class SearchEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;

    public SearchEquipamentoUseCase(EquipamentoGateway equipamentoGateway) {
        this.equipamentoGateway = equipamentoGateway;
    }

    public List<Equipamento> execute() {
        return this.equipamentoGateway.findAll().stream().map(EquipamentoSchema::toEquipamento).toList();
    }

}
