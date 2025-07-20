package com.fiap.hackaton.infrastructure.leito.controller;

import com.fiap.hackaton.entity.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.infrastructure.leito.dto.LeitoPublicData;
import com.fiap.hackaton.infrastructure.leito.dto.LeitoUpdateData;
import com.fiap.hackaton.usecase.leito.UpdateLeitoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateLeitoController {

    private final UpdateLeitoUseCase updateLeitoUseCase;

    public UpdateLeitoController(UpdateLeitoUseCase updateLeitoUseCase) {
        this.updateLeitoUseCase = updateLeitoUseCase;
    }

    @PutMapping("/leitos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeitoPublicData updateLeito(@PathVariable UUID id, @RequestBody LeitoUpdateData dados) throws LeitoNotFoundException {
        return new LeitoPublicData(updateLeitoUseCase.execute(id, dados));
    }

}
