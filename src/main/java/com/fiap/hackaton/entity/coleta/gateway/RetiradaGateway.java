package com.fiap.hackaton.entity.coleta.gateway;

import com.fiap.hackaton.entity.coleta.model.Coleta;

import java.util.List;
import java.util.Optional;

public interface RetiradaGateway {
    Coleta save(Coleta coleta);
    Optional<Coleta> findById(Long id);
    List<Coleta> findAll();
    void deleteById(Long id);
}