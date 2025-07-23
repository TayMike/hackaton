package com.fiap.hackaton.usecase.equipamento;

import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;

import java.util.UUID;

public class DeleteEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;

    public DeleteEquipamentoUseCase(EquipamentoGateway equipamentoGateway) {
        this.equipamentoGateway = equipamentoGateway;
    }

    public void execute(UUID id) throws EquipamentoNotFoundException {
        equipamentoGateway.findById(id)
                .orElseThrow(EquipamentoNotFoundException::new).toEquipamento();

        equipamentoGateway.deleteById(id);
    }

}
