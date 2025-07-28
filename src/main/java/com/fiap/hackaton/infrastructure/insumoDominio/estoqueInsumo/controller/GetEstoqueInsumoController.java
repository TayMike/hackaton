package com.fiap.hackaton.infrastructure.insumoDominio.estoqueInsumo.controller;

import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.infrastructure.insumoDominio.estoqueInsumo.dto.EstoqueInsumoPublicData;
import com.fiap.hackaton.usecase.insumoDominio.estoqueInsumo.GetEstoqueInsumoUseCase;
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

    @GetMapping("/estoque-insumos/{hospitalId}")
    @ResponseStatus(HttpStatus.OK)
    public EstoqueInsumoPublicData getEstoqueInsumo(@PathVariable UUID hospitalId) throws EstoqueInsumoNotFoundException {
        EstoqueInsumo estoqueInsumo = getEstoqueInsumoUseCase.execute(hospitalId);
        return new EstoqueInsumoPublicData(estoqueInsumo);
    }

}
