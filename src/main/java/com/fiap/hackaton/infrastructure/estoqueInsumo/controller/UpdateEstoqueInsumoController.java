package com.fiap.hackaton.infrastructure.estoqueInsumo.controller;

import com.fiap.hackaton.entity.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.infrastructure.estoqueInsumo.dto.EstoqueInsumoPublicData;
import com.fiap.hackaton.infrastructure.estoqueInsumo.dto.EstoqueInsumoUpdateData;
import com.fiap.hackaton.usecase.estoqueInsumo.UpdateEstoqueInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateEstoqueInsumoController {

    private final UpdateEstoqueInsumoUseCase updateEstoqueInsumoUseCase;

    public UpdateEstoqueInsumoController(UpdateEstoqueInsumoUseCase updateEstoqueInsumoUseCase) {
        this.updateEstoqueInsumoUseCase = updateEstoqueInsumoUseCase;
    }

    @PutMapping("/estoqueInsumos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstoqueInsumoPublicData updateEstoqueInsumo(@PathVariable UUID id, @RequestBody EstoqueInsumoUpdateData dados) throws EstoqueInsumoNotFoundException {
        return new EstoqueInsumoPublicData(updateEstoqueInsumoUseCase.execute(id, dados));
    }

}
