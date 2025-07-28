package com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.controller;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.dto.EstoqueEquipamentoPublicData;
import com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.dto.EstoqueEquipamentoUpdateData;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.UpdateEstoqueEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UpdateEstoqueEquipamentoController {

    private final UpdateEstoqueEquipamentoUseCase updateEstoqueEquipamentoUseCase;

    public UpdateEstoqueEquipamentoController(UpdateEstoqueEquipamentoUseCase updateEstoqueEquipamentoUseCase) {
        this.updateEstoqueEquipamentoUseCase = updateEstoqueEquipamentoUseCase;
    }

    @PutMapping("/estoque-equipamentos/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EstoqueEquipamentoPublicData updateEstoqueEquipamento(@PathVariable UUID id, @RequestBody EstoqueEquipamentoUpdateData dados) throws EstoqueEquipamentoNotFoundException, ColaboradorNotFoundException {
        return new EstoqueEquipamentoPublicData(updateEstoqueEquipamentoUseCase.execute(id, dados));
    }

}
