package com.fiap.hackaton.infrastructure.core.hospital.gateway;

import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.HospitalRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.HospitalSchema;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HospitalDatabaseGateway implements HospitalGateway {

    private final HospitalRepository hospitalRepository;

    public HospitalDatabaseGateway(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    @Caching(
        evict = {@CacheEvict(cacheNames = "hospitais", allEntries = true, beforeInvocation = true)},
        put = {@CachePut(cacheNames = "hospital", key = "#hospital.id")}
    )
    public Hospital save(Hospital hospital) {
        return hospitalRepository.save(new HospitalSchema(hospital)).toEntity();
    }

    @Override
    @Cacheable(value = "hospital", key = "#id")
    public Optional<Hospital> findById(UUID id) {
        return hospitalRepository.findById(id).map(HospitalSchema::toEntity);
    }

    @Override
    @Cacheable(value = "hospitais", key = "'hospitais'")
    public List<Hospital> findAll() {
        return hospitalRepository.findAll().stream().map(HospitalSchema::toEntity).toList();
    }

    @Override
    @Caching(
            evict = {@CacheEvict(cacheNames = "hospitais", allEntries = true, beforeInvocation = true)},
            put = {@CachePut(cacheNames = "hospital", key = "#hospital.id")}
    )
    public Hospital update(Hospital hospital) {
        return hospitalRepository.save(new HospitalSchema(hospital)).toEntity();
    }

    @Override
    @CacheEvict(cacheNames = {"hospital", "hospitais"}, allEntries = true, beforeInvocation = true)
    public void deleteById(UUID id) {
        hospitalRepository.deleteById(id);
    }
}