package com.fiap.hackaton.infrastructure.coleta.gateway;

import com.fiap.hackaton.entity.coleta.gateway.ColetaGateway;
import com.fiap.hackaton.entity.coleta.model.Coleta;
import com.fiap.hackaton.infrastructure.config.db.repository.ColetaRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.ColetaSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ColetaDatabaseGateway implements ColetaGateway {

    private final ColetaRepository coletaRepository;

    public ColetaDatabaseGateway(ColetaRepository coletaRepository) {
        this.coletaRepository = coletaRepository;
    }

    @Override
    public Coleta save(Coleta coleta) {
        ColetaSchema saved = coletaRepository.save(new ColetaSchema(coleta));
        return saved.toColeta();
    }

    @Override
    public Optional<Coleta> findById(UUID id) {
        return coletaRepository.findById(id)
                .map(ColetaSchema::toColeta);
    }

    @Override
    public List<Coleta> findAll() {
        return coletaRepository.findAll()
                .stream()
                .map(ColetaSchema::toColeta)
                .toList();
    }
}
