package com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.dto.IEstoqueEquipamentoUpdateData;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class UpdateEstoqueEquipamentoUseCase {

    private final EstoqueEquipamentoGateway estoqueEquipamentoGateway;
    private final ColaboradorGateway colaboradorGateway;

    public UpdateEstoqueEquipamentoUseCase(EstoqueEquipamentoGateway estoqueEquipamentoGateway, ColaboradorGateway colaboradorGateway) {
        this.estoqueEquipamentoGateway = estoqueEquipamentoGateway;
        this.colaboradorGateway = colaboradorGateway;
    }

    @Transactional
    public EstoqueEquipamento execute(UUID id, IEstoqueEquipamentoUpdateData dados) throws EstoqueEquipamentoNotFoundException, ColaboradorNotFoundException {
        EstoqueEquipamento estoqueEquipamento = this.estoqueEquipamentoGateway.findById(id)
                .orElseThrow(() -> new EstoqueEquipamentoNotFoundException("Estoque not found: " + id));

        Colaborador colaboradorEntregador = this.colaboradorGateway.findById(dados.colaboradorEntregadorId())
                .orElseThrow(() -> new ColaboradorNotFoundException("Colaborador entregador not found: " + dados.colaboradorEntregadorId()));

        Colaborador colaboradorResponsavel = this.colaboradorGateway.findById(dados.colaboradorResponsavelId())
                .orElseThrow(() -> new ColaboradorNotFoundException("Colaborador responsavel not found: " + dados.colaboradorResponsavelId()));

        if (dados.dataHoraColeta() == null)
            throw new EstoqueEquipamentoNotFoundException("DataHoraColeta não pode ser null");

        estoqueEquipamento.setColaboradorEntregadorId(dados.colaboradorEntregadorId());
        estoqueEquipamento.setDataHoraColeta(dados.dataHoraColeta());
        estoqueEquipamento.setColaboradorResponsavelId(dados.colaboradorResponsavelId());

        return this.estoqueEquipamentoGateway.update(estoqueEquipamento);
    }
}
