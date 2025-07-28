package com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.controller;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.dto.EquipamentoPublicData;
import com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.dto.EquipamentoRegistrationData;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.CreateEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateEquipamentoController {

    private final CreateEquipamentoUseCase createEquipamentoUseCase;

    public CreateEquipamentoController(CreateEquipamentoUseCase createEquipamentoUseCase) {
        this.createEquipamentoUseCase = createEquipamentoUseCase;
    }

    @PostMapping("/equipamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public EquipamentoPublicData createEquipamento(@RequestBody EquipamentoRegistrationData dados) throws HospitalNotFoundException {
        return new EquipamentoPublicData(createEquipamentoUseCase.execute(dados));
    }

}
