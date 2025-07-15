package com.fiap.hackaton.entity.insumo.gateway;

import com.fiap.hackaton.entity.insumo.model.Insumo;

import java.util.List;
import java.util.Optional;

public interface InsumoGateway {
    Insumo save(Insumo insumo);
    Optional<Insumo> findById(Long id);
    List<Insumo> findAll();
    void deleteById(Long id);
}