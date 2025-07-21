package com.fiap.hackaton.entity.coletaEquipamento.gateway;

import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.infrastructure.config.db.schema.ColaboradorSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.ColetaEquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.EquipamentoSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColetaEquipamentoGateway {

    ColetaEquipamentoSchema save(ColetaEquipamento coletaEquipamento,
                                 List<EquipamentoSchema> equipamentos,
                                 ColaboradorSchema colaboradorEntregador,
                                 ColaboradorSchema colaboradorResponsavel,
                                 HospitalSchema hospital);

    Optional<ColetaEquipamentoSchema> findById(UUID id);

    List<ColetaEquipamentoSchema> findAll();

}
