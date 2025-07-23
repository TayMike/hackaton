package com.fiap.hackaton.infrastructure.insumo.controller;

import com.fiap.hackaton.entity.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.usecase.insumo.DeleteInsumoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteInsumoController {

    private final DeleteInsumoUseCase deleteInsumoUserCase;

    public DeleteInsumoController(DeleteInsumoUseCase deleteInsumoUserCase) {
        this.deleteInsumoUserCase = deleteInsumoUserCase;
    }

    @DeleteMapping("/insumos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteInsumo(@PathVariable UUID id) throws InsumoNotFoundException {
        deleteInsumoUserCase.execute(id);
    }

}
