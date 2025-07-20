package com.fiap.hackaton.infrastructure.estoqueInsumo.controller;

import com.fiap.hackaton.infrastructure.estoqueInsumo.dto.EstoqueInsumoPublicData;
import com.fiap.hackaton.infrastructure.estoqueInsumo.dto.EstoqueInsumoRegistrationData;
import com.fiap.hackaton.usecase.estoqueInsumo.CreateEstoqueInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateEstoqueInsumoController {

    private final CreateEstoqueInsumoUseCase createEstoqueInsumoUseCase;

    public CreateEstoqueInsumoController(CreateEstoqueInsumoUseCase createEstoqueInsumoUseCase) {
        this.createEstoqueInsumoUseCase = createEstoqueInsumoUseCase;
    }

    @PostMapping("/estoqueInsumos")
    @ResponseStatus(HttpStatus.CREATED)
    public EstoqueInsumoPublicData createEstoqueInsumo(@RequestBody EstoqueInsumoRegistrationData dados) {
        return new EstoqueInsumoPublicData(createEstoqueInsumoUseCase.execute(dados));
    }

}
