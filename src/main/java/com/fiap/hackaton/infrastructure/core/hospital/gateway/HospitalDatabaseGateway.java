package com.fiap.hackaton.infrastructure.core.hospital.gateway;

import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.HospitalRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HospitalDatabaseGateway implements HospitalGateway {

    private final HospitalRepository hospitalRepository;

    public HospitalDatabaseGateway(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(new HospitalSchema(hospital)).toEntity();
    }

    @Override
    public Optional<Hospital> findById(UUID id) {
        return hospitalRepository.findById(id).map(HospitalSchema::toEntity);
    }

    @Override
    public List<Hospital> findAll() {
        return hospitalRepository.findAll().stream().map(HospitalSchema::toEntity).toList();
    }

    @Override
    public Hospital update(Hospital hospital) {
        return hospitalRepository.save(new HospitalSchema(hospital)).toEntity();
    }

    @Override
    public void deleteById(UUID id) {
        hospitalRepository.deleteById(id);
    }
}