package com.fiap.hackaton.infrastructure.hospital.gateway;

import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.config.db.repository.HospitalRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class HospitalDatabaseGateway implements HospitalGateway {

    private final HospitalRepository hospitalRepository;

    public HospitalDatabaseGateway(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Hospital save(Hospital hospital) {
        HospitalSchema saved = hospitalRepository.save(new HospitalSchema(hospital));
        return saved.toHospital();
    }

    @Override
    public Optional<HospitalSchema> findById(UUID id) {
        return hospitalRepository.findById(id);
    }

    @Override
    public List<HospitalSchema> findAll() {
        return hospitalRepository.findAll();
    }

    @Override
    public Hospital update(Hospital hospital) {
        HospitalSchema updated = hospitalRepository.save(new HospitalSchema(hospital));
        return updated.toHospital();
    }

    @Override
    public void deleteById(UUID id) {
        hospitalRepository.deleteById(id);
    }
}