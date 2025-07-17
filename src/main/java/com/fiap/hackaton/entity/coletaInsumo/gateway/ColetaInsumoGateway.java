package com.fiap.hackaton.entity.coletaInsumo.gateway;

import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColetaInsumoGateway {
    ColetaInsumo save(ColetaInsumo coletaInsumo);
    Optional<ColetaInsumo> findById(UUID id);
    List<ColetaInsumo> findAll();
}