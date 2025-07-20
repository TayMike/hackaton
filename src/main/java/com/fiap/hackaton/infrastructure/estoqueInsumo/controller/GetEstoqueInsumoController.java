package com.fiap.hackaton.infrastructure.estoqueInsumo.controller;

import com.fiap.hackaton.entity.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.estoqueInsumo.dto.EstoqueInsumoPublicData;
import com.fiap.hackaton.usecase.estoqueInsumo.GetEstoqueInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetEstoqueInsumoController {

    private final GetEstoqueInsumoUseCase getEstoqueInsumoUseCase;

    public GetEstoqueInsumoController(GetEstoqueInsumoUseCase getEstoqueInsumoUseCase) {
        this.getEstoqueInsumoUseCase = getEstoqueInsumoUseCase;
    }

    @GetMapping("/estoqueInsumos/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EstoqueInsumoPublicData getEstoqueInsumo(@PathVariable UUID id) throws EstoqueInsumoNotFoundException {
        EstoqueInsumo estoqueInsumo = getEstoqueInsumoUseCase.execute(id);
        return new EstoqueInsumoPublicData(estoqueInsumo);
    }

}
