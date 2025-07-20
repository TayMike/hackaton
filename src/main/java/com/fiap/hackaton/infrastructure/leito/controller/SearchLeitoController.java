package com.fiap.hackaton.infrastructure.leito.controller;

import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.infrastructure.leito.dto.LeitoPublicData;
import com.fiap.hackaton.usecase.leito.SearchLeitoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchLeitoController {

    private final SearchLeitoUseCase searchLeitoUseCase;

    public SearchLeitoController(SearchLeitoUseCase searchLeitoUseCase) {
        this.searchLeitoUseCase = searchLeitoUseCase;
    }

    @GetMapping("/leitos")
    @ResponseStatus(HttpStatus.OK)
    public List<LeitoPublicData> searchLeito() {
        List<Leito> leitos = this.searchLeitoUseCase.execute();
        return leitos.stream().map(LeitoPublicData::new).toList();
    }

}
