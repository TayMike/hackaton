package com.fiap.hackaton.infrastructure.leito.controller;

import com.fiap.hackaton.entity.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.infrastructure.leito.dto.LeitoPublicData;
import com.fiap.hackaton.usecase.leito.GetLeitoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetLeitoController {

    private final GetLeitoUseCase getLeitoUseCase;

    public GetLeitoController(GetLeitoUseCase getLeitoUseCase) {
        this.getLeitoUseCase = getLeitoUseCase;
    }

    @GetMapping("/leitos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeitoPublicData getLeito(@PathVariable UUID id) throws LeitoNotFoundException {
        Leito leito = getLeitoUseCase.execute(id);
        return new LeitoPublicData(leito);
    }

}
