package com.fiap.hackaton.infrastructure.insumo.controller;

import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.insumo.dto.InsumoPublicData;
import com.fiap.hackaton.usecase.insumo.SearchInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchInsumoController {

    private final SearchInsumoUseCase searchInsumoUseCase;

    public SearchInsumoController(SearchInsumoUseCase searchInsumoUseCase) {
        this.searchInsumoUseCase = searchInsumoUseCase;
    }

    @GetMapping("/insumos")
    @ResponseStatus(HttpStatus.OK)
    public List<InsumoPublicData> searchInsumo() {
        List<Insumo> insumos = this.searchInsumoUseCase.execute();
        return insumos.stream().map(InsumoPublicData::new).toList();
    }

}
