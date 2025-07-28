package com.fiap.hackaton.infrastructure.insumoDominio.entregaInsumo.controller;

import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.infrastructure.insumoDominio.entregaInsumo.dto.EntregaInsumoPublicData;
import com.fiap.hackaton.usecase.insumoDominio.entregaInsumo.SearchEntregaInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchEntregaInsumoController {

    private final SearchEntregaInsumoUseCase searchEntregaInsumoUseCase;

    public SearchEntregaInsumoController(SearchEntregaInsumoUseCase searchEntregaInsumoUseCase) {
        this.searchEntregaInsumoUseCase = searchEntregaInsumoUseCase;
    }

    @GetMapping("/entrega-insumos")
    @ResponseStatus(HttpStatus.OK)
    public List<EntregaInsumoPublicData> searchEntregaInsumo() {
        List<EntregaInsumo> entregaInsumoes = this.searchEntregaInsumoUseCase.execute();
        return entregaInsumoes.stream().map(EntregaInsumoPublicData::new).toList();
    }

}
