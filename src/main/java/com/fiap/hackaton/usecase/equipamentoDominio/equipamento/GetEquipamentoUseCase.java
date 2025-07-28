package com.fiap.hackaton.usecase.equipamentoDominio.equipamento;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;

    public GetEquipamentoUseCase(EquipamentoGateway equipamentoGateway) {
        this.equipamentoGateway = equipamentoGateway;
    }

    @Transactional(readOnly = true)
    public Equipamento execute(UUID id) throws EquipamentoNotFoundException {
        return this.equipamentoGateway.findById(id).orElseThrow(() -> new EquipamentoNotFoundException("Equipamento not found: " + id));
    }

}
