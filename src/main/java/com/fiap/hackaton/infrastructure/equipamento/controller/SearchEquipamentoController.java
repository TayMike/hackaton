package com.fiap.hackaton.infrastructure.equipamento.controller;

import com.fiap.hackaton.entity.equipamento.model.Equipamento;
import com.fiap.hackaton.infrastructure.equipamento.dto.EquipamentoPublicData;
import com.fiap.hackaton.usecase.equipamento.SearchEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchEquipamentoController {

    private final SearchEquipamentoUseCase searchEquipamentoUseCase;

    public SearchEquipamentoController(SearchEquipamentoUseCase searchEquipamentoUseCase) {
        this.searchEquipamentoUseCase = searchEquipamentoUseCase;
    }

    @GetMapping("/equipamentos")
    @ResponseStatus(HttpStatus.OK)
    public List<EquipamentoPublicData> searchEquipamento() {
        List<Equipamento> equipamentos = this.searchEquipamentoUseCase.execute();
        return equipamentos.stream().map(EquipamentoPublicData::new).toList();
    }

}
