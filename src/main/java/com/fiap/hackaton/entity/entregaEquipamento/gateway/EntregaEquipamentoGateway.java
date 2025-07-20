package com.fiap.hackaton.entity.entregaEquipamento.gateway;

import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntregaEquipamentoGateway {

    EntregaEquipamento save(EntregaEquipamento EntregaEquipamento);

    Optional<EntregaEquipamento> findById(UUID id);

    List<EntregaEquipamento> findAll();

}
