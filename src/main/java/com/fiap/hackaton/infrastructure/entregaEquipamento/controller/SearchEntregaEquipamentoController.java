package com.fiap.hackaton.infrastructure.entregaEquipamento.controller;

import com.fiap.hackaton.entity.entregaEquipamento.model.EntregaEquipamento;
import com.fiap.hackaton.infrastructure.entregaEquipamento.dto.EntregaEquipamentoPublicData;
import com.fiap.hackaton.usecase.entregaEquipamento.SearchEntregaEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchEntregaEquipamentoController {

    private final SearchEntregaEquipamentoUseCase searchEntregaEquipamentoUseCase;

    public SearchEntregaEquipamentoController(SearchEntregaEquipamentoUseCase searchEntregaEquipamentoUseCase) {
        this.searchEntregaEquipamentoUseCase = searchEntregaEquipamentoUseCase;
    }

    @GetMapping("/entregaEquipamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<EntregaEquipamentoPublicData> searchEntregaEquipamento() {
        List<EntregaEquipamento> entregaEquipamentoes = this.searchEntregaEquipamentoUseCase.execute();
        return entregaEquipamentoes.stream().map(EntregaEquipamentoPublicData::new).toList();
    }

}