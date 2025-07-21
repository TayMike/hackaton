package com.fiap.hackaton.usecase.coletaEquipamento;

import com.fiap.hackaton.entity.coletaEquipamento.gateway.ColetaEquipamentoGateway;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.ColetaEquipamentoSchema;

import java.util.List;

public class SearchColetaEquipamentoUseCase {

    private final ColetaEquipamentoGateway coletaEquipamentoGateway;

    public SearchColetaEquipamentoUseCase(ColetaEquipamentoGateway coletaEquipamentoGateway) {
        this.coletaEquipamentoGateway = coletaEquipamentoGateway;
    }

    public List<ColetaEquipamento> execute() {
        return this.coletaEquipamentoGateway.findAll().stream().map(ColetaEquipamentoSchema::toColetaEquipamento).toList();
    }

}
