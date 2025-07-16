package com.fiap.hackaton.entity.estoque.gateway;

import com.fiap.hackaton.entity.estoque.model.Estoque;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueGateway<T> {
    Estoque<T> save(Estoque<T> estoque);
    Optional<Estoque<T>> findById(UUID id);
    List<Estoque<T>> findAll();
    Estoque<T> update(Estoque<T> estoque);
    void deleteById(UUID id);
}