package com.fiap.hackaton.usecase.descarteEquipamento;

import com.fiap.hackaton.entity.descarteEquipamento.gateway.DescarteEquipamentoGateway;
import com.fiap.hackaton.entity.descarteEquipamento.model.DescarteEquipamento;
import com.fiap.hackaton.usecase.descarteEquipamento.dto.IDescarteEquipamentoRegistrationData;

public class CreateDescarteEquipamentoUseCase {

    private final DescarteEquipamentoGateway descarteEquipamentoGateway;

    public CreateDescarteEquipamentoUseCase(DescarteEquipamentoGateway descarteEquipamentoGateway) {
        this.descarteEquipamentoGateway = descarteEquipamentoGateway;
    }

    public DescarteEquipamento execute(IDescarteEquipamentoRegistrationData dados) {

        DescarteEquipamento descarteEquipamento = new DescarteEquipamento(dados.equipamentos(), dados.quantidade(), dados.colaboradorResponsavel(),
                dados.dataHoraDescarte(), dados.hospital());

        return this.descarteEquipamentoGateway.save(descarteEquipamento);
    }

}