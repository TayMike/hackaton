package com.fiap.hackaton.infrastructure.config.db.repository;

import com.fiap.hackaton.entity.insumo.model.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, UUID> {
}
