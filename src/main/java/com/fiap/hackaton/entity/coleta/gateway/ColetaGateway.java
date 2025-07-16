package com.fiap.hackaton.entity.coleta.gateway;

import com.fiap.hackaton.entity.coleta.model.Coleta;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColetaGateway {
    Coleta save(Coleta coleta);
    Optional<Coleta> findById(UUID id);
    List<Coleta> findAll();
}