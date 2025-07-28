package com.fiap.hackaton.entity.equipamentoDominio.equipamento.gateway;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EquipamentoGateway {

    Equipamento save(Equipamento equipamento);
    Optional<Equipamento> findById(UUID id);
    List<Equipamento> findAll();
    Equipamento update(Equipamento equipamento);
    void deleteById(UUID id);

}