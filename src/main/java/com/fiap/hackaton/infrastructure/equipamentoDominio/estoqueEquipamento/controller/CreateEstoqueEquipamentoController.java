package com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.controller;

import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.equipamento.exception.EquipamentoNotFoundException;
import com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.dto.EstoqueEquipamentoPublicData;
import com.fiap.hackaton.infrastructure.equipamentoDominio.estoqueEquipamento.dto.EstoqueEquipamentoRegistrationData;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.CreateEstoqueEquipamentoUseCase;
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

    @PostMapping("/estoque-equipamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public EstoqueEquipamentoPublicData createEstoqueEquipamento(@RequestBody EstoqueEquipamentoRegistrationData dados) throws HospitalNotFoundException, EquipamentoNotFoundException {
        return new EstoqueEquipamentoPublicData(createEstoqueEquipamentoUseCase.execute(dados));
    }

}
