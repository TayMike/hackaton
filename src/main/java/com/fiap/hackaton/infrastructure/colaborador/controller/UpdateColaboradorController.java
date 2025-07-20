package com.fiap.hackaton.infrastructure.colaborador.controller;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.infrastructure.colaborador.dto.ColaboradorPublicData;
import com.fiap.hackaton.infrastructure.colaborador.dto.ColaboradorUpdateData;
import com.fiap.hackaton.usecase.colaborador.UpdateColaboradorUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateColaboradorController {

    private final UpdateColaboradorUseCase updateColaboradorUseCase;

    public UpdateColaboradorController(UpdateColaboradorUseCase updateColaboradorUseCase) {
        this.updateColaboradorUseCase = updateColaboradorUseCase;
    }

    @PutMapping("/admins/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ColaboradorPublicData updateColaborador(@PathVariable UUID id, @Valid @RequestBody ColaboradorUpdateData dados) throws ColaboradorNotFoundException {
        return new ColaboradorPublicData(updateColaboradorUseCase.execute(id, dados));
    }

}
