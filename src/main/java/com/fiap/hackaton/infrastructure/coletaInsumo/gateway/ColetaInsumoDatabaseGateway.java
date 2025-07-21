package com.fiap.hackaton.infrastructure.coletaInsumo.gateway;

import com.fiap.hackaton.entity.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.config.db.repository.ColetaInsumoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ColetaInsumoDatabaseGateway implements ColetaInsumoGateway {

    private final ColetaInsumoRepository coletaInsumoRepository;

    public ColetaInsumoDatabaseGateway(ColetaInsumoRepository coletaInsumoRepository) {
        this.coletaInsumoRepository = coletaInsumoRepository;
    }

    @Override
    public ColetaInsumoSchema save(ColetaInsumo coletaInsumo, List<InsumoSchema> insumoSchemas, ColaboradorSchema colaboradorEntregador,
                             PacienteSchema pacienteRecebedor, HospitalSchema hospital) {
        return coletaInsumoRepository.save(new ColetaInsumoSchema(coletaInsumo, insumoSchemas, colaboradorEntregador, pacienteRecebedor, hospital));
    }

    @Override
    public Optional<ColetaInsumoSchema> findById(UUID id) {
        return coletaInsumoRepository.findById(id);
    }

    @Override
    public List<ColetaInsumoSchema> findAll() {
        return coletaInsumoRepository.findAll().stream().toList();
    }
}
