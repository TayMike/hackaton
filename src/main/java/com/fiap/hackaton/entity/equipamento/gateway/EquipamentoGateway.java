package com.fiap.hackaton.entity.equipamento.gateway;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipamentoGateway {

    EquipamentoSchema save(Equipamento equipamento, HospitalSchema hospitalSchema);
    Optional<EquipamentoSchema> findById(UUID id);
    List<EquipamentoSchema> findAll();
    EquipamentoSchema update(Equipamento equipamento, HospitalSchema hospitalSchema);
    void deleteById(UUID id);

}