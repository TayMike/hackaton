package com.fiap.hackaton.infrastructure.estoqueEquipamento.controller;

import com.fiap.hackaton.infrastructure.estoqueEquipamento.dto.EstoqueEquipamentoPublicData;
import com.fiap.hackaton.infrastructure.estoqueEquipamento.dto.EstoqueEquipamentoRegistrationData;
import com.fiap.hackaton.usecase.estoqueEquipamento.CreateEstoqueEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateEstoqueEquipamentoController {

    private final CreateEstoqueEquipamentoUseCase createEstoqueEquipamentoUseCase;

    public CreateEstoqueEquipamentoController(CreateEstoqueEquipamentoUseCase createEstoqueEquipamentoUseCase) {
        this.createEstoqueEquipamentoUseCase = createEstoqueEquipamentoUseCase;
    }

    @PostMapping("/estoqueEquipamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public EstoqueEquipamentoPublicData createEstoqueEquipamento(@RequestBody EstoqueEquipamentoRegistrationData dados) {
        return new EstoqueEquipamentoPublicData(createEstoqueEquipamentoUseCase.execute(dados));
    }

}
