package com.fiap.hackaton.infrastructure.insumo.controller;

import com.fiap.hackaton.entity.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.infrastructure.insumo.dto.InsumoPublicData;
import com.fiap.hackaton.infrastructure.insumo.dto.InsumoUpdateData;
import com.fiap.hackaton.usecase.insumo.UpdateInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateInsumoController {

    private final UpdateInsumoUseCase updateInsumoUseCase;

    public UpdateInsumoController(UpdateInsumoUseCase updateInsumoUseCase) {
        this.updateInsumoUseCase = updateInsumoUseCase;
    }

    @PutMapping("/insumos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InsumoPublicData updateInsumo(@PathVariable UUID id, @RequestBody InsumoUpdateData dados) throws InsumoNotFoundException {
        return new InsumoPublicData(updateInsumoUseCase.execute(id, dados));
    }

}
