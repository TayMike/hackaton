package com.fiap.hackaton.infrastructure.coletaInsumo.controller;

import com.fiap.hackaton.entity.coletaInsumo.exception.ColetaInsumoNotFoundException;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.coletaInsumo.dto.ColetaInsumoPublicData;
import com.fiap.hackaton.usecase.coletaInsumo.GetColetaInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetColetaInsumoController {

    private final GetColetaInsumoUseCase getColetaInsumoUseCase;

    public GetColetaInsumoController(GetColetaInsumoUseCase getColetaInsumoUseCase) {
        this.getColetaInsumoUseCase = getColetaInsumoUseCase;
    }

    @GetMapping("/coletaInsumos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ColetaInsumoPublicData getColetaInsumo(@PathVariable UUID id) throws ColetaInsumoNotFoundException {
        ColetaInsumo coletaInsumo = getColetaInsumoUseCase.execute(id);
        return new ColetaInsumoPublicData(coletaInsumo);
    }

}
