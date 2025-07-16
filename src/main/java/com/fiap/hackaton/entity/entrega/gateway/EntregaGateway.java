package com.fiap.hackaton.entity.entrega.gateway;

import com.fiap.hackaton.entity.entrega.model.Entrega;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntregaGateway {
    Entrega save(Entrega entrega);
    Optional<Entrega> findById(UUID id);
    List<Entrega> findAll();
}