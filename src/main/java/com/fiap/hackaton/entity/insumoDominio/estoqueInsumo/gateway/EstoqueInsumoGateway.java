package com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway;

import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueInsumoGateway {
    EstoqueInsumo save(EstoqueInsumo estoqueInsumo);
    List<EstoqueInsumo> findAll();
    Optional<EstoqueInsumo> findByHospitalId(UUID hospitalId);
}