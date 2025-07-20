package com.fiap.hackaton.usecase.coletaEquipamento;

import com.fiap.hackaton.entity.coletaEquipamento.gateway.ColetaEquipamentoGateway;
import com.fiap.hackaton.entity.coletaEquipamento.model.ColetaEquipamento;
import com.fiap.hackaton.usecase.coletaEquipamento.dto.IColetaEquipamentoRegistrationData;

public class CreateColetaEquipamentoUseCase {

    private final ColetaEquipamentoGateway coletaEquipamentoGateway;

    public CreateColetaEquipamentoUseCase(ColetaEquipamentoGateway coletaEquipamentoGateway) {
        this.coletaEquipamentoGateway = coletaEquipamentoGateway;
    }

    public ColetaEquipamento execute(IColetaEquipamentoRegistrationData dados) {

        ColetaEquipamento coletaEquipamento = new ColetaEquipamento(dados.equipamentos(), dados.quantidades(), dados.colaboradorEntregador(),
                dados.dataHoraColeta(), dados.colaboradorResponsavel(), dados.hospital());

        return this.coletaEquipamentoGateway.save(coletaEquipamento);
    }

}
