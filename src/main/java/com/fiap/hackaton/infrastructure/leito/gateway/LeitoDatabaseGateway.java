package com.fiap.hackaton.infrastructure.leito.gateway;

import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.infrastructure.config.db.repository.LeitoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.LeitoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LeitoDatabaseGateway implements LeitoGateway {

    private final LeitoRepository leitoRepository;

    public LeitoDatabaseGateway(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }

    @Override
    public LeitoSchema save(Leito leito, HospitalSchema hospitalSchema, PacienteSchema pacienteSchema) {
        return leitoRepository.save(new LeitoSchema(leito, hospitalSchema, pacienteSchema));
    }

    @Override
    public Optional<LeitoSchema> findById(UUID id) {
        return leitoRepository.findById(id);
    }

    @Override
    public List<LeitoSchema> findAll() {
        return leitoRepository.findAll();
    }

    @Override
    public LeitoSchema update(Leito leito, HospitalSchema hospitalSchema, PacienteSchema pacienteSchema) {
        return leitoRepository.save(new LeitoSchema(leito, hospitalSchema, pacienteSchema));
    }

    @Override
    public void deleteById(UUID id) {
        leitoRepository.deleteById(id);
    }

}
