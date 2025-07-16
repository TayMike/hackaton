package com.fiap.hackaton.usecase.colaborador;

import com.fiap.hackaton.entity.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import com.fiap.hackaton.usecase.colaborador.dto.IColaboradorUpdateData;

import java.util.UUID;

public class UpdateColaboradorUseCase {

    private final ColaboradorGateway colaboradorGateway;

    public UpdateColaboradorUseCase(ColaboradorGateway colaboradorGateway) {
        this.colaboradorGateway = colaboradorGateway;
    }

    public Colaborador execute(UUID id, IColaboradorUpdateData dados) throws ColaboradorNotFoundException {
        Colaborador colaborador = this.colaboradorGateway.findById(id)
                .orElseThrow(ColaboradorNotFoundException::new);

        if (dados.nome() != null && !dados.nome().isBlank())
            colaborador.setNome(dados.nome());

        if (dados.cep() != null && !dados.cep().isBlank())
            colaborador.setCep(dados.cep());

        if (dados.numeroCasa() != null)
            colaborador.setNumeroCasa(dados.numeroCasa());

        if (dados.setor() != null && !dados.setor().isBlank())
            colaborador.setSetor(dados.setor());

        if (dados.ativo() != null)
            colaborador.setAtivo(dados.ativo());

        return this.colaboradorGateway.update(colaborador);
    }

}
