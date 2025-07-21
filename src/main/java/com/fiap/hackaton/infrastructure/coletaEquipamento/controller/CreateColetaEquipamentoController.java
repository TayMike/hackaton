package com.fiap.hackaton.infrastructure.coletaEquipamento.controller;

import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.infrastructure.coletaEquipamento.dto.ColetaEquipamentoPublicData;
import com.fiap.hackaton.infrastructure.coletaEquipamento.dto.ColetaEquipamentoRegistrationData;
import com.fiap.hackaton.usecase.coletaEquipamento.CreateColetaEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateColetaEquipamentoController {

    private final CreateColetaEquipamentoUseCase createColetaEquipamentoUseCase;

    public CreateColetaEquipamentoController(CreateColetaEquipamentoUseCase createColetaEquipamentoUseCase) {
        this.createColetaEquipamentoUseCase = createColetaEquipamentoUseCase;
    }

    @PostMapping("/coletaEquipamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public ColetaEquipamentoPublicData createColetaEquipamento(@RequestBody ColetaEquipamentoRegistrationData dados) throws EquipamentoNotFoundException {
        return new ColetaEquipamentoPublicData(createColetaEquipamentoUseCase.execute(dados));
    }

}
