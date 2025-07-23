package com.fiap.hackaton.infrastructure.leito.controller;

import com.fiap.hackaton.entity.leito.exception.LeitoNotFoundException;
import com.fiap.hackaton.usecase.leito.DeleteLeitoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteLeitoController {

    private final DeleteLeitoUseCase deleteLeitoUserCase;

    public DeleteLeitoController(DeleteLeitoUseCase deleteLeitoUserCase) {
        this.deleteLeitoUserCase = deleteLeitoUserCase;
    }

    @DeleteMapping("/leitos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLeito(@PathVariable UUID id) throws LeitoNotFoundException {
        deleteLeitoUserCase.execute(id);
    }

}
