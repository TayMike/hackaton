package com.fiap.hackaton.infrastructure.coletaEquipamento.controller;

import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.infrastructure.coletaEquipamento.dto.ColetaEquipamentoPublicData;
import com.fiap.hackaton.usecase.coletaEquipamento.SearchColetaEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchColetaEquipamentoController {

    private final SearchColetaEquipamentoUseCase searchColetaEquipamentoUseCase;

    public SearchColetaEquipamentoController(SearchColetaEquipamentoUseCase searchColetaEquipamentoUseCase) {
        this.searchColetaEquipamentoUseCase = searchColetaEquipamentoUseCase;
    }

    @GetMapping("/coletaEquipamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<ColetaEquipamentoPublicData> searchColetaEquipamento() {
        List<ColetaEquipamento> coletaEquipamentoes = this.searchColetaEquipamentoUseCase.execute();
        return coletaEquipamentoes.stream().map(ColetaEquipamentoPublicData::new).toList();
    }

}
