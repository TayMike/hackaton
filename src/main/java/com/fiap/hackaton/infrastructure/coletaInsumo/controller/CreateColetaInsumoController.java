package com.fiap.hackaton.infrastructure.coletaInsumo.controller;

import com.fiap.hackaton.infrastructure.coletaInsumo.dto.ColetaInsumoPublicData;
import com.fiap.hackaton.infrastructure.coletaInsumo.dto.ColetaInsumoRegistrationData;
import com.fiap.hackaton.usecase.coletaInsumo.CreateColetaInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateColetaInsumoController {

    private final CreateColetaInsumoUseCase createColetaInsumoUseCase;

    public CreateColetaInsumoController(CreateColetaInsumoUseCase createColetaInsumoUseCase) {
        this.createColetaInsumoUseCase = createColetaInsumoUseCase;
    }

    @PostMapping("/coletaInsumos")
    @ResponseStatus(HttpStatus.CREATED)
    public ColetaInsumoPublicData createColetaInsumo(@RequestBody ColetaInsumoRegistrationData dados) {
        return new ColetaInsumoPublicData(createColetaInsumoUseCase.execute(dados));
    }

}
