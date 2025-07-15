package com.fiap.hackaton.infrastructure.config.db.repository;

import com.fiap.hackaton.entity.estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstoqueRepository<T> extends JpaRepository<Estoque<T>, UUID> {
}