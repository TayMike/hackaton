package com.fiap.hackaton.entity.coletaEquipamento.gateway;

import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ColetaEquipamentoGateway {

    ColetaEquipamento save(ColetaEquipamento coletaEquipamento);

    Optional<ColetaEquipamento> findById(UUID id);

    List<ColetaEquipamento> findAll();

}
