package com.fiap.hackaton.entity.hospital.gateway;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HospitalGateway {
    HospitalSchema save(Hospital hospital, List<ColaboradorSchema> colaboradores);
    Optional<HospitalSchema> findById(UUID id);
    List<HospitalSchema> findAll();
    HospitalSchema update(Hospital hospital, List<ColaboradorSchema> colaboradores);
    void deleteById(UUID id);
}