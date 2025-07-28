package com.fiap.hackaton.usecase.insumoDominio.insumo;

import com.fiap.hackaton.entity.insumoDominio.insumo.exception.InsumoNotFoundException;
import com.fiap.hackaton.entity.insumoDominio.insumo.gateway.InsumoGateway;
import com.fiap.hackaton.entity.insumoDominio.insumo.model.Insumo;
import com.fiap.hackaton.usecase.insumoDominio.insumo.dto.IInsumoUpdateData;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateInsumoUseCase {

    private final InsumoGateway insumoGateway;

    public UpdateInsumoUseCase(InsumoGateway insumoGateway) {
        this.insumoGateway = insumoGateway;
    }

    @Transactional
    public Insumo execute(UUID id, IInsumoUpdateData dados) throws InsumoNotFoundException {
        Insumo insumo = this.insumoGateway.findById(id)
                .orElseThrow(() -> new InsumoNotFoundException("Insumo not found: " + id));

        if (dados.nome() != null && !dados.nome().isBlank())
            insumo.setNome(dados.nome());

        if (dados.custo() != null && !dados.custo().equals(new BigDecimal(0)))
            insumo.setCusto(dados.custo());

        if (dados.peso() != null && !dados.peso().equals(0L))
            insumo.setPeso(dados.peso());

        if (dados.validade() != null)
            insumo.setValidade(dados.validade());

        if (dados.marca() != null && !dados.marca().isBlank())
            insumo.setMarca(dados.marca());

        if (dados.unidadeMedida() != null)
            insumo.setUnidadeMedida(dados.unidadeMedida());

        return this.insumoGateway.update(insumo);
    }

}
