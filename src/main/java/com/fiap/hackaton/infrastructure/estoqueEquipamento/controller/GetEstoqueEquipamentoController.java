package com.fiap.hackaton.infrastructure.estoqueEquipamento.controller;

import com.fiap.hackaton.entity.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.estoqueEquipamento.dto.EstoqueEquipamentoPublicData;
import com.fiap.hackaton.usecase.estoqueEquipamento.GetEstoqueEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetEstoqueEquipamentoController {

    private final GetEstoqueEquipamentoUseCase getEstoqueEquipamentoUseCase;

    public GetEstoqueEquipamentoController(GetEstoqueEquipamentoUseCase getEstoqueEquipamentoUseCase) {
        this.getEstoqueEquipamentoUseCase = getEstoqueEquipamentoUseCase;
    }

    @GetMapping("/estoqueEquipamentos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EstoqueEquipamentoPublicData getEstoqueEquipamento(@PathVariable UUID id) throws EstoqueEquipamentoNotFoundException {
        EstoqueEquipamento estoqueEquipamento = getEstoqueEquipamentoUseCase.execute(id);
        return new EstoqueEquipamentoPublicData(estoqueEquipamento);
    }

}
