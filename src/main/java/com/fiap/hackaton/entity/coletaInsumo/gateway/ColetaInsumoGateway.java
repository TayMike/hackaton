package com.fiap.hackaton.entity.coletaInsumo.gateway;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.PacienteSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColetaInsumoGateway {

    ColetaInsumo save(ColetaInsumo coletaInsumo, List<InsumoSchema> insumoSchemas,
                      ColaboradorSchema colaboradorEntregador, PacienteSchema pacienteRecebedor,
                      HospitalSchema hospital);

    Optional<ColetaInsumo> findById(UUID id);

    List<ColetaInsumo> findAll();

}