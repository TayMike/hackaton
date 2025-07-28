package com.fiap.hackaton.infrastructure.core.leito.gateway;

import com.fiap.hackaton.entity.core.leito.gateway.LeitoGateway;
import com.fiap.hackaton.entity.core.leito.model.Leito;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.LeitoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.LeitoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LeitoDatabaseGateway implements LeitoGateway {

    private final LeitoRepository leitoRepository;

    public LeitoDatabaseGateway(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }

    @Override
    public Leito save(Leito leito) {
        return leitoRepository.save(new LeitoSchema(leito)).toEntity();
    }

    @Override
    public Optional<Leito> findById(UUID id) {
        return leitoRepository.findById(id).map(LeitoSchema::toEntity);
    }

    @Override
    public List<Leito> findAll() {
        return leitoRepository.findAll().stream().map(LeitoSchema::toEntity).toList();
    }

    @Override
    public Leito update(Leito leito) {
        return leitoRepository.save(new LeitoSchema(leito)).toEntity();
    }

    @Override
    public void deleteById(UUID id) {
        leitoRepository.deleteById(id);
    }

}
