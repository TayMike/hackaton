package com.fiap.hackaton.entity.entrega.gateway;

import com.fiap.hackaton.entity.entrega.model.Entrega;

import java.util.List;
import java.util.Optional;

public interface EntregaGateway {
    Entrega save(Entrega entrega);
    Optional<Entrega> findById(Long id);
    List<Entrega> findAll();
    void deleteById(Long id);
}