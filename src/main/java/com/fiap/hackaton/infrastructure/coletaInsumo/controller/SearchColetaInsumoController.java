package com.fiap.hackaton.infrastructure.coletaInsumo.controller;

import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.coletaInsumo.dto.ColetaInsumoPublicData;
import com.fiap.hackaton.usecase.coletaInsumo.SearchColetaInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchColetaInsumoController {

    private final SearchColetaInsumoUseCase searchColetaInsumoUseCase;

    public SearchColetaInsumoController(SearchColetaInsumoUseCase searchColetaInsumoUseCase) {
        this.searchColetaInsumoUseCase = searchColetaInsumoUseCase;
    }

    @GetMapping("/coletaInsumos")
    @ResponseStatus(HttpStatus.OK)
    public List<ColetaInsumoPublicData> searchColetaInsumo() {
        List<ColetaInsumo> coletaInsumoes = this.searchColetaInsumoUseCase.execute();
        return coletaInsumoes.stream().map(ColetaInsumoPublicData::new).toList();
    }

}
