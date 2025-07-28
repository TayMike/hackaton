package com.fiap.hackaton.infrastructure.equipamentoDominio.entregaEquipamento.controller;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.entregaEquipamento.exception.EntregaEquipamentoNotFoundException;
import com.fiap.hackaton.infrastructure.equipamentoDominio.entregaEquipamento.dto.EntregaEquipamentoPublicData;
import com.fiap.hackaton.infrastructure.equipamentoDominio.entregaEquipamento.dto.EntregaEquipamentoRegistrationData;
import com.fiap.hackaton.usecase.equipamentoDominio.entregaEquipamento.CreateEntregaEquipamentoUseCase;
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

    @PostMapping("/entrega-equipamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaEquipamentoPublicData createEntregaEquipamento(@RequestBody EntregaEquipamentoRegistrationData dados) throws HospitalNotFoundException, ColaboradorNotFoundException, EntregaEquipamentoNotFoundException {
        return new EntregaEquipamentoPublicData(createEntregaEquipamentoUseCase.execute(dados));
    }

}
