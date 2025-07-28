package com.fiap.hackaton.usecase.equipamentoDominio.equipamento;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway.EquipamentoGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DeleteEquipamentoUseCase {

    private final EquipamentoGateway equipamentoGateway;

    public DeleteEquipamentoUseCase(EquipamentoGateway equipamentoGateway) {
        this.equipamentoGateway = equipamentoGateway;
    }

    @Transactional
    public void execute(UUID id) throws EquipamentoNotFoundException {
        equipamentoGateway.findById(id).orElseThrow(() -> new EquipamentoNotFoundException("Equipamento not found: " + id));

        equipamentoGateway.deleteById(id);
    }

}
