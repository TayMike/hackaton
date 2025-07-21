package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;
import com.fiap.hackaton.usecase.estoqueInsumo.dto.IEstoqueInsumoRegistrationData;

import java.util.List;

public class CreateEstoqueInsumoUseCase {

    private final EstoqueInsumoGateway estoqueGateway;
    private final InsumoGateway insumoGateway;
    private final HospitalGateway hospitalGateway;

    public CreateEstoqueInsumoUseCase(EstoqueInsumoGateway estoqueGateway, InsumoGateway insumoGateway, HospitalGateway hospitalGateway) {
        this.estoqueGateway = estoqueGateway;
        this.insumoGateway = insumoGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public EstoqueInsumo execute(IEstoqueInsumoRegistrationData dados) throws HospitalNotFoundException {

        List<InsumoSchema> itens = this.insumoGateway.findAll().stream()
                .filter(insumo -> dados.itens().contains(insumo.getId()))
                .toList();
        List<Insumo> insumos = itens.stream()
                .map(InsumoSchema::toInsumo)
                .toList();

        HospitalSchema hospitalSchema = this.hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        EstoqueInsumo estoqueInsumo = new EstoqueInsumo(insumos, dados.quantidades(), hospitalSchema.toHospital());

        return this.estoqueGateway.save(estoqueInsumo, itens, hospitalSchema).toEstoqueInsumo();
    }

}
