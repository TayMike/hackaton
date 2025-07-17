package com.fiap.hackaton.infrastructure.leito.gateway;

import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.entity.leito.gateway.LeitoGateway;
import com.fiap.hackaton.infrastructure.config.db.repository.LeitoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.LeitoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class LeitoDatabaseGateway implements LeitoGateway {

    private final LeitoRepository leitoRepository;

    public LeitoDatabaseGateway(LeitoRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }

    @Override
    public Leito save(Leito leito) {
        LeitoSchema saved = leitoRepository.save(new LeitoSchema(leito));
        return saved.toLeito();
    }

    @Override
    public Optional<Leito> findById(UUID id) {
        return leitoRepository.findById(id).map(LeitoSchema::toLeito);
    }

    @Override
    public List<Leito> findAll() {
        return leitoRepository.findAll()
                .stream()
                .map(LeitoSchema::toLeito)
                .collect(Collectors.toList());
    }

    @Override
    public Leito update(Leito leito) {
        LeitoSchema updated = leitoRepository.save(new LeitoSchema(leito));
        return updated.toLeito();
    }

    @Override
    public void deleteById(UUID id) {
        leitoRepository.deleteById(id);
    }

}
