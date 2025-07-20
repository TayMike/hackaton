package com.fiap.hackaton.entity.descarteEquipamento.gateway;

import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DescarteEquipamentoGateway {

    DescarteEquipamento save(DescarteEquipamento equipamento);

    Optional<DescarteEquipamento> findById(UUID id);

    List<DescarteEquipamento> findAll();

}
