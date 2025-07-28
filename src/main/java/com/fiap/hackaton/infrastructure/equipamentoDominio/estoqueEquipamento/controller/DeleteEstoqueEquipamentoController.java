package com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.controller;

import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.DeleteEstoqueEquipamentoUseCase;
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

    @DeleteMapping("/estoque-equipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEstoqueEquipamento(@PathVariable UUID id) throws EstoqueEquipamentoNotFoundException {
        deleteEstoqueEquipamentoUserCase.execute(id);
    }

}
