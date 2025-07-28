package com.fiap.hackaton.infrastructure.insumoDominio.insumo.controller;

import com.fiap.hackaton.infrastructure.insumoDominio.insumo.dto.InsumoPublicData;
import com.fiap.hackaton.infrastructure.insumoDominio.insumo.dto.InsumoRegistrationData;
import com.fiap.hackaton.usecase.insumoDominio.insumo.CreateInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateInsumoController {

    private final CreateInsumoUseCase createInsumoUseCase;

    public CreateInsumoController(CreateInsumoUseCase createInsumoUseCase) {
        this.createInsumoUseCase = createInsumoUseCase;
    }

    @PostMapping("/insumos")
    @ResponseStatus(HttpStatus.CREATED)
    public InsumoPublicData createInsumo(@RequestBody InsumoRegistrationData dados) {
        return new InsumoPublicData(createInsumoUseCase.execute(dados));
    }

}
