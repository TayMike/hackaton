package com.fiap.hackaton.infrastructure.equipamento.controller;

import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.infrastructure.equipamento.dto.EquipamentoPublicData;
import com.fiap.hackaton.usecase.equipamento.DeleteEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteEquipamentoController {

    private final DeleteEquipamentoUseCase deleteEquipamentoUserCase;

    public DeleteEquipamentoController(DeleteEquipamentoUseCase deleteEquipamentoUserCase) {
        this.deleteEquipamentoUserCase = deleteEquipamentoUserCase;
    }

    @DeleteMapping("/equipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipamentoPublicData deleteEquipamento(@PathVariable UUID id) throws EquipamentoNotFoundException {
        return new EquipamentoPublicData(deleteEquipamentoUserCase.execute(id));
    }

}
