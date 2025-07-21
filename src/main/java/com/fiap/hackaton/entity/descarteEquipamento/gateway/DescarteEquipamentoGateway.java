package com.fiap.hackaton.entity.descarteEquipamento.gateway;

import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DescarteEquipamentoGateway {

    DescarteEquipamento save(DescarteEquipamento descarteEquipamento, List<EquipamentoSchema> equipamentos, ColaboradorSchema colaboradorSchema, HospitalSchema hospitalSchema);

    Optional<DescarteEquipamento> findById(UUID id);

    List<DescarteEquipamento> findAll();

}
