package com.fiap.hackaton.entity.core.colaborador.gateway;

import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColaboradorGateway {
    Colaborador save(Colaborador colaborador);
    Optional<Colaborador> findById(UUID id);
    List<Colaborador> findAll();
    Colaborador update(Colaborador colaborador);
}