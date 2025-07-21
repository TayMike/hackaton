package com.fiap.hackaton.entity.leito.gateway;

import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.LeitoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LeitoGateway {

    LeitoSchema save(Leito leito, HospitalSchema hospitalSchema, PacienteSchema pacienteSchema);
    Optional<LeitoSchema> findById(UUID id);
    List<LeitoSchema> findAll();
    LeitoSchema update(Leito leito, HospitalSchema hospitalSchema, PacienteSchema pacienteSchema);
    void deleteById(UUID id);

}
