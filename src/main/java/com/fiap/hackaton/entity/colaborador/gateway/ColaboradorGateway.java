package com.fiap.hackaton.entity.colaborador.gateway;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColaboradorGateway {
    Colaborador save(Colaborador colaborador, HospitalSchema hospitalSchema);
    Optional<ColaboradorSchema> findById(UUID id);
    List<Colaborador> findAll();
    Colaborador update(Colaborador colaborador, HospitalSchema hospitalSchema);
}