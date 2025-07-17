package com.fiap.hackaton.infrastructure.coletaInsumo.gateway;

import com.fiap.hackaton.entity.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.config.db.repository.ColetaInsumoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.ColetaInsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ColetaDatabaseInsumoGateway implements ColetaInsumoGateway {

    private final ColetaInsumoRepository coletaInsumoRepository;

    public ColetaDatabaseInsumoGateway(ColetaInsumoRepository coletaInsumoRepository) {
        this.coletaInsumoRepository = coletaInsumoRepository;
    }

    @Override
    public ColetaInsumo save(ColetaInsumo coletaInsumo) {
        ColetaInsumoSchema saved = coletaInsumoRepository.save(new ColetaInsumoSchema(coletaInsumo));
        return saved.toColeta();
    }

    @Override
    public Optional<ColetaInsumo> findById(UUID id) {
        return coletaInsumoRepository.findById(id)
                .map(ColetaInsumoSchema::toColeta);
    }

    @Override
    public List<ColetaInsumo> findAll() {
        return coletaInsumoRepository.findAll()
                .stream()
                .map(ColetaInsumoSchema::toColeta)
                .toList();
    }
}
