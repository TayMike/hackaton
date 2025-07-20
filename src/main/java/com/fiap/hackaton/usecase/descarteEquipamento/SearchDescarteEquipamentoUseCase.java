package com.fiap.hackaton.usecase.descarteEquipamento;

import com.fiap.hackaton.entity.descarteEquipamento.gateway.DescarteEquipamentoGateway;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;

import java.util.List;

public class SearchDescarteEquipamentoUseCase {

    private final DescarteEquipamentoGateway descarteEquipamentoGateway;

    public SearchDescarteEquipamentoUseCase(DescarteEquipamentoGateway descarteEquipamentoGateway) {
        this.descarteEquipamentoGateway = descarteEquipamentoGateway;
    }

    public List<DescarteEquipamento> execute() {
        return this.descarteEquipamentoGateway.findAll();
    }

}
