package com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueEquipamentoGateway {

    EstoqueEquipamento save(EstoqueEquipamento estoqueEquipamento);
    Optional<EstoqueEquipamento> findById(UUID id);
    List<EstoqueEquipamento> findAll();
    EstoqueEquipamento update(EstoqueEquipamento estoqueEquipamento);
    void deleteById(UUID id);

}
