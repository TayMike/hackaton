package com.fiap.hackaton.infrastructure.equipamento.controller;

import com.fiap.hackaton.entity.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.infrastructure.equipamento.dto.EquipamentoPublicData;
import com.fiap.hackaton.infrastructure.equipamento.dto.EquipamentoUpdateData;
import com.fiap.hackaton.usecase.equipamento.UpdateEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateEquipamentoController {

    private final UpdateEquipamentoUseCase updateEquipamentoUseCase;

    public UpdateEquipamentoController(UpdateEquipamentoUseCase updateEquipamentoUseCase) {
        this.updateEquipamentoUseCase = updateEquipamentoUseCase;
    }

    @PutMapping("/equipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipamentoPublicData updateEquipamento(@PathVariable UUID id, @RequestBody EquipamentoUpdateData dados) throws EquipamentoNotFoundException {
        return new EquipamentoPublicData(updateEquipamentoUseCase.execute(id, dados));
    }

}
