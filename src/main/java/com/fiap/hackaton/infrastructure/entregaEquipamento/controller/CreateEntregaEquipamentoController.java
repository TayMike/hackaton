package com.fiap.hackaton.infrastructure.entregaEquipamento.controller;

import com.fiap.hackaton.infrastructure.entregaEquipamento.dto.EntregaEquipamentoPublicData;
import com.fiap.hackaton.infrastructure.entregaEquipamento.dto.EntregaEquipamentoRegistrationData;
import com.fiap.hackaton.usecase.entregaEquipamento.CreateEntregaEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateEntregaEquipamentoController {

    private final CreateEntregaEquipamentoUseCase createEntregaEquipamentoUseCase;

    public CreateEntregaEquipamentoController(CreateEntregaEquipamentoUseCase createEntregaEquipamentoUseCase) {
        this.createEntregaEquipamentoUseCase = createEntregaEquipamentoUseCase;
    }

    @PostMapping("/entregaEquipamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaEquipamentoPublicData createEntregaEquipamento(@RequestBody EntregaEquipamentoRegistrationData dados) {
        return new EntregaEquipamentoPublicData(createEntregaEquipamentoUseCase.execute(dados));
    }

}
