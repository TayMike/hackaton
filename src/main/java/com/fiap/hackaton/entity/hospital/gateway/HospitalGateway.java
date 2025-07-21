package com.fiap.hackaton.entity.hospital.gateway;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HospitalGateway {
    Hospital save(Hospital hospital);
    Optional<HospitalSchema> findById(UUID id);
    List<HospitalSchema> findAll();
    Hospital update(Hospital hospital);
    void deleteById(UUID id);
}