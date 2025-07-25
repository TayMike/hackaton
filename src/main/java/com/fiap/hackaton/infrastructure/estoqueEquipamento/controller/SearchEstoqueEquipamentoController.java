package com.fiap.hackaton.infrastructure.estoqueEquipamento.controller;

import com.fiap.hackaton.entity.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.infrastructure.estoqueEquipamento.dto.EstoqueEquipamentoPublicData;
import com.fiap.hackaton.usecase.estoqueEquipamento.SearchEstoqueEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchEstoqueEquipamentoController {

    private final SearchEstoqueEquipamentoUseCase searchEstoqueEquipamentoUseCase;

    public SearchEstoqueEquipamentoController(SearchEstoqueEquipamentoUseCase searchEstoqueEquipamentoUseCase) {
        this.searchEstoqueEquipamentoUseCase = searchEstoqueEquipamentoUseCase;
    }

    @GetMapping("/estoqueEquipamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<EstoqueEquipamentoPublicData> searchEstoqueEquipamento() {
        List<EstoqueEquipamento> estoqueEquipamentos = this.searchEstoqueEquipamentoUseCase.execute();
        return estoqueEquipamentos.stream().map(EstoqueEquipamentoPublicData::new).toList();
    }
}
