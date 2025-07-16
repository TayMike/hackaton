package com.fiap.hackaton.entity.estoqueInsumo.gateway;

import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueInsumoGateway {
    EstoqueInsumo save(EstoqueInsumo estoqueInsumo);
    Optional<EstoqueInsumo> findById(UUID id);
    List<EstoqueInsumo> findAll();
    EstoqueInsumo update(EstoqueInsumo estoqueInsumo);
    void deleteById(UUID id);
}