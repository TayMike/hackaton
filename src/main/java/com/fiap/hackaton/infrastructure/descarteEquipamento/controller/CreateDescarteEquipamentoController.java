package com.fiap.hackaton.infrastructure.descarteEquipamento.controller;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.infrastructure.descarteEquipamento.dto.DescarteEquipamentoPublicData;
import com.fiap.hackaton.infrastructure.descarteEquipamento.dto.DescarteEquipamentoRegistrationData;
import com.fiap.hackaton.usecase.descarteEquipamento.CreateDescarteEquipamentoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateDescarteEquipamentoController {

    private final CreateDescarteEquipamentoUseCase createDescarteEquipamentoUseCase;

    public CreateDescarteEquipamentoController(CreateDescarteEquipamentoUseCase createDescarteEquipamentoUseCase) {
        this.createDescarteEquipamentoUseCase = createDescarteEquipamentoUseCase;
    }

    @PostMapping("/descarteEquipamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public DescarteEquipamentoPublicData createDescarteEquipamento(@RequestBody DescarteEquipamentoRegistrationData dados) throws HospitalNotFoundException, ColaboradorNotFoundException {
        return new DescarteEquipamentoPublicData(createDescarteEquipamentoUseCase.execute(dados));
    }

}
