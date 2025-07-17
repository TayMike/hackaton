package com.fiap.hackaton.usecase.equipamento;

import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamento.gateway.EquipamentoGateway;
import com.fiap.hackaton.entity.equipamento.model.Equipamento;

import java.util.UUID;

public class DeleteEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;

    public DeleteEquipamentoUseCase(EquipamentoGateway equipamentoGateway) {
        this.equipamentoGateway = equipamentoGateway;
    }

    public Equipamento execute(UUID id) throws EquipamentoNotFoundException {
        Equipamento equipamento = equipamentoGateway.findById(id)
                .orElseThrow(EquipamentoNotFoundException::new);

        equipamentoGateway.deleteById(id);

        return equipamento;
    }

}
