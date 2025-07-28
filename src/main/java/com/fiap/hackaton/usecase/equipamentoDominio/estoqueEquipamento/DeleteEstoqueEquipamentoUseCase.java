package com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class DeleteEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;

    public DeleteEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    @Transactional
    public void execute(UUID id) throws EstoqueEquipamentoNotFoundException {
        estoqueGateway.findById(id)
                .orElseThrow(() -> new EstoqueEquipamentoNotFoundException("Estoque de Equipamento not found: " + id));

        estoqueGateway.deleteById(id);
    }

}
