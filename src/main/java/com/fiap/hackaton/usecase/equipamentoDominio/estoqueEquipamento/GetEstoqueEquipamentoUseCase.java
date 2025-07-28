package com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class GetEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueGateway;

    public GetEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    @Transactional(readOnly = true)
    public EstoqueEquipamento execute(UUID id) throws EstoqueEquipamentoNotFoundException {
        return this.estoqueGateway.findById(id).
                orElseThrow(() -> new EstoqueEquipamentoNotFoundException("Estoque de equipamento not found: " + id));
    }

}
