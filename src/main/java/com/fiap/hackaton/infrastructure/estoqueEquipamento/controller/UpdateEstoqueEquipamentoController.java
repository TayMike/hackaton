package com.fiap.hackaton.infrastructure.estoqueEquipamento.controller;

import com.fiap.hackaton.entity.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.infrastructure.estoqueEquipamento.dto.EstoqueEquipamentoPublicData;
import com.fiap.hackaton.infrastructure.estoqueEquipamento.dto.EstoqueEquipamentoUpdateData;
import com.fiap.hackaton.usecase.estoqueEquipamento.UpdateEstoqueEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateEstoqueEquipamentoController {

    private final UpdateEstoqueEquipamentoUseCase updateEstoqueEquipamentoUseCase;

    public UpdateEstoqueEquipamentoController(UpdateEstoqueEquipamentoUseCase updateEstoqueEquipamentoUseCase) {
        this.updateEstoqueEquipamentoUseCase = updateEstoqueEquipamentoUseCase;
    }

    @PutMapping("/estoqueEquipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstoqueEquipamentoPublicData updateEstoqueEquipamento(@PathVariable UUID id, @RequestBody EstoqueEquipamentoUpdateData dados) throws EstoqueEquipamentoNotFoundException, HospitalNotFoundException {
        return new EstoqueEquipamentoPublicData(updateEstoqueEquipamentoUseCase.execute(id, dados));
    }

}
