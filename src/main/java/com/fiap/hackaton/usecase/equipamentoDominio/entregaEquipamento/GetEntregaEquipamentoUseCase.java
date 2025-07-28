package com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento;

import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.exception.EntregaEquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.gateway.EntregaEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model.EntregaEquipamento;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetEntregaEquipamentoUseCase {

    private final EntregaEquipamentoGateway entregaEquipamentoGateway;

    public GetEntregaEquipamentoUseCase(EntregaEquipamentoGateway entregaEquipamentoGateway) {
        this.entregaEquipamentoGateway = entregaEquipamentoGateway;
    }

    @Transactional(readOnly = true)
    public EntregaEquipamento execute(UUID id) throws EntregaEquipamentoNotFoundException {
        return this.entregaEquipamentoGateway.findById(id).
                orElseThrow(() -> new EntregaEquipamentoNotFoundException("Entrega Equipamento not found: " + id));
    }

}
