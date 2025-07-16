package com.fiap.hackaton.entity.hospital.gateway;

import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HospitalGateway {
    Hospital save(Hospital hospital);
    Optional<Hospital> findById(UUID id);
    List<Hospital> findAll();
    Hospital update(Hospital hospital);
    void deleteById(UUID id);
}