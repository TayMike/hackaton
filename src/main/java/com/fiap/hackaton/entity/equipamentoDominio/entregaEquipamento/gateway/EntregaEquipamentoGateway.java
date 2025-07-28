package com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.gateway;

import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.model.EntregaEquipamento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntregaEquipamentoGateway {

    EntregaEquipamento save(EntregaEquipamento entregaEquipamento);

    Optional<EntregaEquipamento> findById(UUID id);

    List<EntregaEquipamento> findAll();

}
