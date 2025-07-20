package com.fiap.hackaton.infrastructure.entregaInsumo.controller;

import com.fiap.hackaton.infrastructure.entregaInsumo.dto.EntregaInsumoPublicData;
import com.fiap.hackaton.infrastructure.entregaInsumo.dto.EntregaInsumoRegistrationData;
import com.fiap.hackaton.usecase.entregaInsumo.CreateEntregaInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateEntregaInsumoController {

    private final CreateEntregaInsumoUseCase createEntregaInsumoUseCase;

    public CreateEntregaInsumoController(CreateEntregaInsumoUseCase createEntregaInsumoUseCase) {
        this.createEntregaInsumoUseCase = createEntregaInsumoUseCase;
    }

    @PostMapping("/entregaInsumos")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaInsumoPublicData createEntregaInsumo(@RequestBody EntregaInsumoRegistrationData dados) {
        return new EntregaInsumoPublicData(createEntregaInsumoUseCase.execute(dados));
    }

}
