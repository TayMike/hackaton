package com.fiap.hackaton.infrastructure.entregaEquipamento.controller;

import com.fiap.hackaton.entity.entregaEquipamento.exception.EntregaEquipamentoNotFoundException;
import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.infrastructure.entregaEquipamento.dto.EntregaEquipamentoPublicData;
import com.fiap.hackaton.usecase.entregaEquipamento.GetEntregaEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetEntregaEquipamentoController {

    private final GetEntregaEquipamentoUseCase getEntregaEquipamentoUseCase;

    public GetEntregaEquipamentoController(GetEntregaEquipamentoUseCase getEntregaEquipamentoUseCase) {
        this.getEntregaEquipamentoUseCase = getEntregaEquipamentoUseCase;
    }

    @GetMapping("/entregaEquipamentos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaEquipamentoPublicData getEntregaEquipamento(@PathVariable UUID id) throws EntregaEquipamentoNotFoundException {
        EntregaEquipamento entregaEquipamento = getEntregaEquipamentoUseCase.execute(id);
        return new EntregaEquipamentoPublicData(entregaEquipamento);
    }

}