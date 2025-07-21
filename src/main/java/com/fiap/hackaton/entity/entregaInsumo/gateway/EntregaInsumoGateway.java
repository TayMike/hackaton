package com.fiap.hackaton.entity.entregaInsumo.gateway;

import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntregaInsumoGateway {

    EntregaInsumo save(EntregaInsumo entregaInsumo, List<InsumoSchema> insumos, ColaboradorSchema colaboradorRecebedorSchema, HospitalSchema hospitalSchema);

    Optional<EntregaInsumo> findById(UUID id);

    List<EntregaInsumo> findAll();

}