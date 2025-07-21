package com.fiap.hackaton.usecase.estoqueInsumo;

import com.fiap.hackaton.entity.estoqueInsumo.exception.EstoqueInsumoNotFoundException;
import com.fiap.hackaton.entity.estoqueInsumo.gateway.EstoqueInsumoGateway;
import com.fiap.hackaton.entity.estoqueInsumo.model.EstoqueInsumo;
import com.fiap.hackaton.entity.hospital.exception.HospitalNotFoundException;
import com.fiap.hackaton.entity.hospital.gateway.HospitalGateway;
import com.fiap.hackaton.entity.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumo.model.Insumo;
import com.fiap.hackaton.infrastructure.config.db.schema.HospitalSchema;
import com.fiap.hackaton.infrastructure.config.db.schema.InsumoSchema;
import com.fiap.hackaton.usecase.estoqueInsumo.dto.IEstoqueInsumoUpdateData;

import java.util.List;
import java.util.UUID;

public class UpdateEstoqueInsumoUseCase {

    private final EstoqueInsumoGateway estoqueGateway;
    private final InsumoGateway insumoGateway;
    private final HospitalGateway hospitalGateway;

    public UpdateEstoqueInsumoUseCase(EstoqueInsumoGateway estoqueGateway, InsumoGateway insumoGateway, HospitalGateway hospitalGateway) {
        this.estoqueGateway = estoqueGateway;
        this.insumoGateway = insumoGateway;
        this.hospitalGateway = hospitalGateway;
    }

    public EstoqueInsumo execute(UUID id, IEstoqueInsumoUpdateData dados) throws EstoqueInsumoNotFoundException, HospitalNotFoundException {
        EstoqueInsumo estoqueInsumo = this.estoqueGateway.findById(id)
                .orElseThrow(EstoqueInsumoNotFoundException::new).toEstoqueInsumo();

        List<InsumoSchema> itens = this.insumoGateway.findAll().stream()
                .filter(insumo -> dados.itens() != null && dados.itens().contains(insumo.getId()))
                .toList();
        List<Insumo> insumos = itens.stream()
                .map(InsumoSchema::toInsumo)
                .toList();

        HospitalSchema hospitalSchema = this.hospitalGateway.findById(dados.hospital())
                .orElseThrow(HospitalNotFoundException::new);

        if (!dados.itens().isEmpty())
            estoqueInsumo.setItens(insumos);

        if (dados.quantidades() != null && !dados.quantidades().isEmpty())
            estoqueInsumo.setQuantidades(dados.quantidades());

        if (dados.hospital() != null)
            estoqueInsumo.setHospital(hospitalSchema.toHospital());

        return this.estoqueGateway.update(estoqueInsumo, itens, hospitalSchema).toEstoqueInsumo();
    }

}
