package com.fiap.hackaton.entity.leito.gateway;

import com.fiap.hackaton.entity.leito.model.Leito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeitoGateway {

    Leito save(Leito leito);
    Optional<Leito> findById(UUID id);
    List<Leito> findAll();
    Leito update(Leito leito);
    void deleteById(UUID id);

}
