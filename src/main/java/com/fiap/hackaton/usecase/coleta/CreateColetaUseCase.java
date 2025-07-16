package com.fiap.hackaton.usecase.coleta;

import com.fiap.hackaton.entity.coleta.gateway.ColetaGateway;
import com.fiap.hackaton.entity.coleta.model.Coleta;
import com.fiap.hackaton.usecase.coleta.dto.IColetaRegistrationData;

public class CreateColetaUseCase {

    private final ColetaGateway coletaGateway;

    public CreateColetaUseCase(ColetaGateway coletaGateway) {
        this.coletaGateway = coletaGateway;
    }

    public Coleta execute(IColetaRegistrationData dados) {

        Coleta coleta = new Coleta(dados.insumo(), dados.quantidade(), dados.colaboradorEntregador(),
                dados.dataHoraColeta(), dados.pacienteRecebedor(), dados.hospital());

        return this.coletaGateway.save(coleta);
    }

}