package com.fiap.hackaton.infrastructure.coletaEquipamento.controller;

import com.fiap.hackaton.entity.coletaEquipamento.exception.ColetaEquipamentoNotFoundException;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.infrastructure.coletaEquipamento.dto.ColetaEquipamentoPublicData;
import com.fiap.hackaton.usecase.coletaEquipamento.GetColetaEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetColetaEquipamentoController {

    private final GetColetaEquipamentoUseCase getColetaEquipamentoUseCase;

    public GetColetaEquipamentoController(GetColetaEquipamentoUseCase getColetaEquipamentoUseCase) {
        this.getColetaEquipamentoUseCase = getColetaEquipamentoUseCase;
    }

    @GetMapping("/coletaEquipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColetaEquipamentoPublicData getColetaEquipamento(@PathVariable UUID id) throws ColetaEquipamentoNotFoundException {
        ColetaEquipamento coletaEquipamento = getColetaEquipamentoUseCase.execute(id);
        return new ColetaEquipamentoPublicData(coletaEquipamento);
    }

}
