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
    public Optional<Hospital> findById(UUID id) {
        return hospitalRepository.findById(id).map(HospitalSchema::toHospital);
    }

    @Override
    public List<Hospital> findAll() {
        return hospitalRepository.findAll()
                .stream()
                .map(HospitalSchema::toHospital)
                .collect(Collectors.toList());
    }

    @Override
    public Hospital update(Hospital hospital) {
        // Assumes update is same as save when the ID exists
        HospitalSchema updated = hospitalRepository.save(new HospitalSchema(hospital));
        return updated.toHospital();
    }

    @Override
    public void deleteById(UUID id) {
        hospitalRepository.deleteById(id);
    }
}