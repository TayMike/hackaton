package com.fiap.hackaton.infrastructure.config.db.repository;

import com.fiap.hackaton.entity.coleta.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColetaRepository extends JpaRepository<Coleta, UUID> {
}