package com.fiap.hackaton.infrastructure.config.db.repository;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
}