package com.fiap.hackaton.usecase.coletaInsumo;

import com.fiap.hackaton.entity.coletaInsumo.gateway.ColetaInsumoGateway;
import com.fiap.hackaton.entity.coletaInsumo.model.ColetaInsumo;
import com.fiap.hackaton.usecase.coletaInsumo.dto.IColetaInsumoRegistrationData;

public class CreateColetaInsumoUseCase {

    private final ColetaInsumoGateway coletaInsumoGateway;

    public CreateColetaInsumoUseCase(ColetaInsumoGateway coletaInsumoGateway) {
        this.coletaInsumoGateway = coletaInsumoGateway;
    }

    public ColetaInsumo execute(IColetaInsumoRegistrationData dados) {

        ColetaInsumo coletaInsumo = new ColetaInsumo(dados.insumos(), dados.quantidades(), dados.colaboradorEntregador(),
                dados.dataHoraColeta(), dados.pacienteRecebedor(), dados.hospital());

        return this.coletaInsumoGateway.save(coletaInsumo);
    }

}