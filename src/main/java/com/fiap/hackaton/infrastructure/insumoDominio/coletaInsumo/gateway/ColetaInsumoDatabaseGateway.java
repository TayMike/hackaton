package com.fiap.hackaton.infrastructure.insumoDominio.coletaInsumo.gateway;

import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.repository.ColetaInsumoRepository;
import com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema.ColetaInsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ColetaInsumoDatabaseGateway implements ColetaInsumoGateway {

    private final ColetaInsumoRepository coletaInsumoRepository;

    public ColetaInsumoDatabaseGateway(ColetaInsumoRepository coletaInsumoRepository) {
        this.coletaInsumoRepository = coletaInsumoRepository;
    }

    @Override
    public ColetaInsumo save(ColetaInsumo coletaInsumo) {
        return coletaInsumoRepository.save(new ColetaInsumoSchema(coletaInsumo)).toEntity();
    }

    @Override
    public Optional<ColetaInsumo> findById(UUID id) {
        return coletaInsumoRepository.findById(id).map(ColetaInsumoSchema::toEntity);
    }

    @Override
    public List<ColetaInsumo> findAll() {
        return coletaInsumoRepository.findAll().stream().map(ColetaInsumoSchema::toEntity).toList();
    }
}
