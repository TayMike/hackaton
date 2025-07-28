package com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.controller;

import com.fiap.hackaton.entity.equipamentoDominio.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.dto.EquipamentoPublicData;
import com.fiap.hackaton.infrastructure.equipamentoDominio.equipamento.dto.EquipamentoUpdateData;
import com.fiap.hackaton.usecase.equipamentoDominio.equipamento.UpdateEquipamentoUseCase;
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
