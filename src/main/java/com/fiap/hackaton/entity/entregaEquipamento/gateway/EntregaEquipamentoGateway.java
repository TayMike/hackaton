package com.fiap.hackaton.entity.entregaEquipamento.gateway;

import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntregaEquipamentoGateway {

    EntregaEquipamento save(EntregaEquipamento entregaEquipamento, List<EquipamentoSchema> equipamentos, ColaboradorSchema colaboradorRecebedorSchema, HospitalSchema hospitalSchema);

    Optional<EntregaEquipamento> findById(UUID id);

    List<EntregaEquipamento> findAll();

}
