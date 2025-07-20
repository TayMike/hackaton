package com.fiap.hackaton.infrastructure.colaborador.controller;

import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.infrastructure.colaborador.dto.ColaboradorPublicData;
import com.fiap.hackaton.usecase.colaborador.SearchColaboradorUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchColaboradorController {

    private final SearchColaboradorUseCase searchColaboradorUseCase;

    public SearchColaboradorController(SearchColaboradorUseCase searchColaboradorUseCase) {
        this.searchColaboradorUseCase = searchColaboradorUseCase;
    }

    @GetMapping("/colaboradores")
    @ResponseStatus(HttpStatus.OK)
    public List<ColaboradorPublicData> searchColaborador() {
        List<Colaborador> colaboradores = this.searchColaboradorUseCase.execute();
        return colaboradores.stream().map(ColaboradorPublicData::new).toList();
    }

}
