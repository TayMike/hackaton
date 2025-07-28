package com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.controller;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.dto.EstoqueEquipamentoPublicData;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.GetEstoqueEquipamentoUseCase;
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

    @GetMapping("/estoque-equipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstoqueEquipamentoPublicData getEstoqueEquipamento(@PathVariable UUID id) throws EstoqueEquipamentoNotFoundException {
        EstoqueEquipamento estoqueEquipamento = getEstoqueEquipamentoUseCase.execute(id);
        return new EstoqueEquipamentoPublicData(estoqueEquipamento);
    }

}
