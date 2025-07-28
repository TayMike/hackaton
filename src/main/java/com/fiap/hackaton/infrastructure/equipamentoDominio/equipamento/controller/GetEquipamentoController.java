package com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.controller;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.model.Equipamento;
import com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.dto.EquipamentoPublicData;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.GetEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetEquipamentoController {

    private final GetEquipamentoUseCase getEquipamentoUseCase;

    public GetEquipamentoController(GetEquipamentoUseCase getEquipamentoUseCase) {
        this.getEquipamentoUseCase = getEquipamentoUseCase;
    }

    @GetMapping("/equipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipamentoPublicData getEquipamento(@PathVariable UUID id) throws EquipamentoNotFoundException {
        Equipamento equipamento = getEquipamentoUseCase.execute(id);
        return new EquipamentoPublicData(equipamento);
    }

}
