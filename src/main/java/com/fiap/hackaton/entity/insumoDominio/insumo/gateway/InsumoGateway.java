package com.fiap.hackaton.entity.insumoDominio.insumo.gateway;

import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InsumoGateway {
    Insumo save(Insumo insumo);
    Optional<Insumo> findById(UUID id);
    List<Insumo> findAll();
    Insumo update(Insumo insumo);
    void deleteById(UUID id);
}