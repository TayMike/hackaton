package com.fiap.hackaton.entity.hospital.gateway;

import com.fiap.hackaton.entity.hospital.model.Hospital;

import java.util.List;
import java.util.Optional;

public interface HospitalGateway {
    Hospital save(Hospital hospital);
    Optional<Hospital> findById(Long id);
    List<Hospital> findAll();
    void deleteById(Long id);
}