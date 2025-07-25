package com.fiap.hackaton.infrastructure.entregaInsumo.controller;

import com.fiap.hackaton.entity.entregaInsumo.exception.EntregaInsumoNotFoundException;
import com.fiap.hackaton.entity.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.infrastructure.entregaInsumo.dto.EntregaInsumoPublicData;
import com.fiap.hackaton.usecase.entregaInsumo.GetEntregaInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetEntregaInsumoController {

    private final GetEntregaInsumoUseCase getEntregaInsumoUseCase;

    public GetEntregaInsumoController(GetEntregaInsumoUseCase getEntregaInsumoUseCase) {
        this.getEntregaInsumoUseCase = getEntregaInsumoUseCase;
    }

    @GetMapping("/entregaInsumos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaInsumoPublicData getEntregaInsumo(@PathVariable UUID id) throws EntregaInsumoNotFoundException {
        EntregaInsumo entregaInsumo = getEntregaInsumoUseCase.execute(id);
        return new EntregaInsumoPublicData(entregaInsumo);
    }

}
