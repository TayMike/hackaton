package com.fiap.hackaton.infrastructure.estoqueEquipamento.controller;

import com.fiap.hackaton.entity.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.infrastructure.estoqueEquipamento.dto.EstoqueEquipamentoPublicData;
import com.fiap.hackaton.usecase.estoqueEquipamento.DeleteEstoqueEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteEstoqueEquipamentoController {

    private final DeleteEstoqueEquipamentoUseCase deleteEstoqueEquipamentoUserCase;

    public DeleteEstoqueEquipamentoController(DeleteEstoqueEquipamentoUseCase deleteEstoqueEquipamentoUserCase) {
        this.deleteEstoqueEquipamentoUserCase = deleteEstoqueEquipamentoUserCase;
    }

    @DeleteMapping("/estoqueEquipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstoqueEquipamentoPublicData deleteEstoqueEquipamento(@PathVariable UUID id) throws EstoqueEquipamentoNotFoundException {
        return new EstoqueEquipamentoPublicData(deleteEstoqueEquipamentoUserCase.execute(id));
    }

}
