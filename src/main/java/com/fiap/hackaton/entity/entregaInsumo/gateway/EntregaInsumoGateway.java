package com.fiap.hackaton.entity.entregaInsumo.gateway;

import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntregaInsumoGateway {
    EntregaInsumo save(EntregaInsumo entregaInsumo);
    Optional<EntregaInsumo> findById(UUID id);
    List<EntregaInsumo> findAll();
}