package com.fiap.hackaton.entity.estoqueEquipamento.gateway;

import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EstoqueEquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueEquipamentoGateway {

    EstoqueEquipamentoSchema save(EstoqueEquipamento estoqueEquipamento, List<EquipamentoSchema> equipamentos, HospitalSchema hospitalSchema);

    Optional<EstoqueEquipamentoSchema> findById(UUID id);

    List<EstoqueEquipamentoSchema> findAll();

    EstoqueEquipamentoSchema update(EstoqueEquipamento estoqueEquipamento, List<EquipamentoSchema> equipamentos, HospitalSchema hospitalSchema);

    void deleteById(UUID id);

}
