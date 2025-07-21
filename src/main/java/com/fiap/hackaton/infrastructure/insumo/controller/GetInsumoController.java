package com.fiap.hackaton.infrastructure.insumo.controller;

import com.fiap.hackaton.entity.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.insumo.dto.InsumoPublicData;
import com.fiap.hackaton.usecase.insumo.GetInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetInsumoController {

    private final GetInsumoUseCase getInsumoUseCase;

    public GetInsumoController(GetInsumoUseCase getInsumoUseCase) {
        this.getInsumoUseCase = getInsumoUseCase;
    }

    @GetMapping("/insumos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InsumoPublicData getInsumo(@PathVariable UUID id) throws InsumoNotFoundException {
        Insumo insumo = getInsumoUseCase.execute(id);
        return new InsumoPublicData(insumo);
    }

}
