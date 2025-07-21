package com.fiap.hackaton.entity.coletaInsumo.gateway;

import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.config.db.schema.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColetaInsumoGateway {

    ColetaInsumoSchema save(ColetaInsumo coletaInsumo, List<InsumoSchema> insumoSchemas,
                            ColaboradorSchema colaboradorEntregador, PacienteSchema pacienteRecebedor,
                            HospitalSchema hospital);

    Optional<ColetaInsumoSchema> findById(UUID id);

    List<ColetaInsumoSchema> findAll();

}