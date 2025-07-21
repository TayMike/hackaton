package com.fiap.hackaton.infrastructure.coletaEquipamento.gateway;

import com.fiap.hackaton.entity.coletaEquipamento.gateway.ColetaEquipamentoGateway;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.infrastructure.config.db.repository.ColetaEquipamentoRepository;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.ColetaEquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ColetaEquipamentoDatabaseGateway implements ColetaEquipamentoGateway {

    private final ColetaEquipamentoRepository coletaEquipamentoRepository;

    public ColetaEquipamentoDatabaseGateway(ColetaEquipamentoRepository coletaEquipamentoRepository) {
        this.coletaEquipamentoRepository = coletaEquipamentoRepository;
    }

    @Override
    public ColetaEquipamentoSchema save(ColetaEquipamento coletaEquipamento,
                                        List<EquipamentoSchema> equipamentos,
                                        ColaboradorSchema colaboradorEntregador,
                                        ColaboradorSchema colaboradorResponsavel,
                                        HospitalSchema hospital) {
        return coletaEquipamentoRepository.save(new ColetaEquipamentoSchema(coletaEquipamento, equipamentos, colaboradorEntregador, colaboradorResponsavel, hospital));
    }

    @Override
    public Optional<ColetaEquipamentoSchema> findById(UUID id) {
        return coletaEquipamentoRepository.findById(id);
    }

    @Override
    public List<ColetaEquipamentoSchema> findAll() {
        return coletaEquipamentoRepository.findAll().stream().toList();
    }
}