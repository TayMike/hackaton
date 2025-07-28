package com.fiap.hackaton.infrastructure.insumoDominio.coletaInsumo.controller;

import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.exception.ColetaInsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.infrastructure.insumoDominio.coletaInsumo.dto.ColetaInsumoPublicData;
import com.fiap.hackaton.usecase.insumoDominio.coletaInsumo.GetColetaInsumoUseCase;
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

    @GetMapping("/coleta-insumos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColetaInsumoPublicData getColetaInsumo(@PathVariable UUID id) throws ColetaInsumoNotFoundException {
        ColetaInsumo coletaInsumo = getColetaInsumoUseCase.execute(id);
        return new ColetaInsumoPublicData(coletaInsumo);
    }

}
