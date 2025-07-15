package com.fiap.hackaton.entity.colaborador.gateway;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;

import java.util.List;
import java.util.Optional;

public interface ColaboradorGateway {
    Colaborador save(Colaborador colaborador);
    Optional<Colaborador> findById(Long id);
    List<Colaborador> findAll();
    void deleteById(Long id);
}