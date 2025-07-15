package com.fiap.hackaton.entity.estoque.gateway;

import com.fiap.hackaton.entity.estoque.model.Estoque;

import java.util.List;
import java.util.Optional;

public interface EstoqueGateway {
    Estoque save(Estoque estoque);
    Optional<Estoque> findById(Long id);
    List<Estoque> findAll();
    void deleteById(Long id);
}