package com.fiap.hackaton.infrastructure.estoqueInsumo.controller;

import com.fiap.hackaton.entity.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.infrastructure.estoqueInsumo.dto.EstoqueInsumoPublicData;
import com.fiap.hackaton.usecase.estoqueInsumo.DeleteEstoqueInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteEstoqueInsumoController {

    private final DeleteEstoqueInsumoUseCase deleteEstoqueInsumoUserCase;

    public DeleteEstoqueInsumoController(DeleteEstoqueInsumoUseCase deleteEstoqueInsumoUserCase) {
        this.deleteEstoqueInsumoUserCase = deleteEstoqueInsumoUserCase;
    }

    @DeleteMapping("/estoqueInsumos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstoqueInsumoPublicData deleteEstoqueInsumo(@PathVariable UUID id) throws EstoqueInsumoNotFoundException {
        return new EstoqueInsumoPublicData(deleteEstoqueInsumoUserCase.execute(id));
    }

}
