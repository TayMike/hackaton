package com.fiap.hackaton.infrastructure.colaborador.controller;

import com.fiap.hackaton.infrastructure.colaborador.dto.ColaboradorPublicData;
import com.fiap.hackaton.infrastructure.colaborador.dto.ColaboradorRegistrationData;
import com.fiap.hackaton.usecase.colaborador.CreateColaboradorUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateColaboradorController {

    private final CreateColaboradorUseCase createColaboradorUseCase;

    public CreateColaboradorController(CreateColaboradorUseCase createColaboradorUseCase) {
        this.createColaboradorUseCase = createColaboradorUseCase;
    }

    @PostMapping("/colaboradores")
    @ResponseStatus(HttpStatus.CREATED)
    public ColaboradorPublicData createColaborador(@Valid @RequestBody ColaboradorRegistrationData dados) {
        return new ColaboradorPublicData(createColaboradorUseCase.execute(dados));
    }

}
