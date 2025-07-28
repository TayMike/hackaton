package com.fiap.hackaton.infrastructure.insumoDominio.coletaInsumo.controller;

import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.insumoDominio.coletaInsumo.dto.ColetaInsumoPublicData;
import com.fiap.hackaton.usecase.insumoDominio.coletaInsumo.SearchColetaInsumoUseCase;
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

    @GetMapping("/coleta-insumos")
    @ResponseStatus(HttpStatus.OK)
    public List<ColetaInsumoPublicData> searchColetaInsumo() {
        List<ColetaInsumo> coletaInsumoes = this.searchColetaInsumoUseCase.execute();
        return coletaInsumoes.stream().map(ColetaInsumoPublicData::new).toList();
    }

}
