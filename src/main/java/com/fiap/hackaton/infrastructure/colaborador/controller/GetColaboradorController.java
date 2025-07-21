package com.fiap.hackaton.infrastructure.colaborador.controller;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.infrastructure.colaborador.dto.ColaboradorPublicData;
import com.fiap.hackaton.usecase.colaborador.GetColaboradorUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GetColaboradorController {

    private final GetColaboradorUseCase getColaboradorUseCase;

    public GetColaboradorController(GetColaboradorUseCase getColaboradorUseCase) {
        this.getColaboradorUseCase = getColaboradorUseCase;
    }

    @GetMapping("/colaboradores/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColaboradorPublicData getColaborador(@PathVariable UUID id) throws ColaboradorNotFoundException {
        Colaborador colaborador = getColaboradorUseCase.execute(id);
        return new ColaboradorPublicData(colaborador);
    }

}
