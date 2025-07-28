package com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento;

import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model.EntregaEquipamento;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class SearchEntregaEquipamentoUseCase {

    private final EntregaEquipamentoGateway entregaEquipamentoGateway;

    public SearchEntregaEquipamentoUseCase(EntregaEquipamentoGateway entregaEquipamentoGateway) {
        this.entregaEquipamentoGateway = entregaEquipamentoGateway;
    }

    @Transactional(readOnly = true)
    public List<EntregaEquipamento> execute() {
        return this.entregaEquipamentoGateway.findAll();
    }

}
