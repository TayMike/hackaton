package com.fiap.hackaton.usecase.insumoDominio.entregaInsumo;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.core.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.core.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.gateway.EntregaInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.entregaInsumo.model.EntregaInsumo;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.usecase.insumoDominio.entregaInsumo.dto.IEntregaInsumoRegistrationData;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public class CreateEntregaInsumoUseCase {

    private final EntregaInsumoGateway entregaInsumoGateway;
    private final ColaboradorGateway colaboradorGateway;
    private final HospitalGateway hospitalGateway;
    private final EstoqueInsumoGateway estoqueInsumoGateway;

    public CreateEntregaInsumoUseCase(EntregaInsumoGateway entregaInsumoGateway, ColaboradorGateway colaboradorGateway,
                                      HospitalGateway hospitalGateway, EstoqueInsumoGateway estoqueInsumoGateway) {
        this.entregaInsumoGateway = entregaInsumoGateway;
        this.colaboradorGateway = colaboradorGateway;
        this.hospitalGateway = hospitalGateway;
        this.estoqueInsumoGateway = estoqueInsumoGateway;
    }

    @Transactional
    public EntregaInsumo execute(IEntregaInsumoRegistrationData dados) throws ColaboradorNotFoundException, HospitalNotFoundException {

        Colaborador colaborador = this.colaboradorGateway.findById(dados.colaboradorRecebedorId())
                .orElseThrow(() -> new ColaboradorNotFoundException("Colaborador not found: " + dados.colaboradorRecebedorId()));

        Hospital hospital = this.hospitalGateway.findById(dados.hospitalId())
                .orElseThrow(() -> new HospitalNotFoundException("Hospital not found: " + dados.hospitalId()));

        EntregaInsumo entregaInsumo = new EntregaInsumo(dados.insumos(), dados.colaboradorRecebedorId(),
                dados.dataHoraRecebimento(), dados.hospitalId());
        entregaInsumo = this.entregaInsumoGateway.save(entregaInsumo);

        EstoqueInsumo estoqueInsumo = estoqueInsumoGateway.findByHospitalId(dados.hospitalId())
                .orElse(new EstoqueInsumo());
        estoqueInsumo.setInsumos(new ArrayList<>());
        estoqueInsumo.setHospitalId(dados.hospitalId());
        estoqueInsumo.aumentarComEntrega(entregaInsumo);
        this.estoqueInsumoGateway.save(estoqueInsumo);

        return entregaInsumo;
    }
}