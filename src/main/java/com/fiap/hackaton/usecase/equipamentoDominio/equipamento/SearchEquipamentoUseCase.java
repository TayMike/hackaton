package com.fiap.hackaton.usecase.equipamentoDominio.equipamento;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;

    public SearchEquipamentoUseCase(EquipamentoGateway equipamentoGateway) {
        this.equipamentoGateway = equipamentoGateway;
    }

    @Transactional(readOnly = true)
    public List<Equipamento> execute() {
        return this.equipamentoGateway.findAll();
    }

}
