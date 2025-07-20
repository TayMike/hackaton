package com.fiap.hackaton.infrastructure.descarteEquipamento.controller;

import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.infrastructure.descarteEquipamento.dto.DescarteEquipamentoPublicData;
import com.fiap.hackaton.usecase.descarteEquipamento.SearchDescarteEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchDescarteEquipamentoController {

    private final SearchDescarteEquipamentoUseCase searchDescarteEquipamentoUseCase;

    public SearchDescarteEquipamentoController(SearchDescarteEquipamentoUseCase searchDescarteEquipamentoUseCase) {
        this.searchDescarteEquipamentoUseCase = searchDescarteEquipamentoUseCase;
    }

    @GetMapping("/descarteEquipamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<DescarteEquipamentoPublicData> searchDescarteEquipamento() {
        List<DescarteEquipamento> descarteEquipamentoes = this.searchDescarteEquipamentoUseCase.execute();
        return descarteEquipamentoes.stream().map(DescarteEquipamentoPublicData::new).toList();
    }

}