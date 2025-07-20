package com.fiap.hackaton.infrastructure.estoqueInsumo.controller;

import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.estoqueInsumo.dto.EstoqueInsumoPublicData;
import com.fiap.hackaton.usecase.estoqueInsumo.SearchEstoqueInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchEstoqueInsumoController {

    private final SearchEstoqueInsumoUseCase searchEstoqueInsumoUseCase;

    public SearchEstoqueInsumoController(SearchEstoqueInsumoUseCase searchEstoqueInsumoUseCase) {
        this.searchEstoqueInsumoUseCase = searchEstoqueInsumoUseCase;
    }

    @GetMapping("/estoqueInsumos")
    @ResponseStatus(HttpStatus.OK)
    public List<EstoqueInsumoPublicData> searchEstoqueInsumo() {
        List<EstoqueInsumo> estoqueInsumos = this.searchEstoqueInsumoUseCase.execute();
        return estoqueInsumos.stream().map(EstoqueInsumoPublicData::new).toList();
    }

}
