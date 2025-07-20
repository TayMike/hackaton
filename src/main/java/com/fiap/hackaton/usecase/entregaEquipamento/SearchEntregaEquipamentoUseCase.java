package com.fiap.hackaton.usecase.entregaEquipamento;

import com.fiap.hackaton.entity.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;

import java.util.List;

public class SearchEntregaEquipamentoUseCase {

    private final EntregaEquipamentoGateway entregaEquipamentoGateway;

    public SearchEntregaEquipamentoUseCase(EntregaEquipamentoGateway entregaEquipamentoGateway) {
        this.entregaEquipamentoGateway = entregaEquipamentoGateway;
    }

    public List<EntregaEquipamento> execute() {
        return this.entregaEquipamentoGateway.findAll();
    }

}
