package com.fiap.hackaton.infrastructure.config.db.repository;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, UUID> {
}
