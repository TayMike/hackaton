package com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento;

import com.fiap.hackaton.entity.core.colaborador.exception.ColaboradorNotFoundException;
import com.fiap.hackaton.entity.core.colaborador.gateway.ColaboradorGateway;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.exception.EstoqueEquipamentoNotFoundException;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.gateway.EstoqueEquipamentoGateway;
import com.fiap.hackaton.entity.equipamentoDominio.estoqueEquipamento.model.EstoqueEquipamento;
import com.fiap.hackaton.usecase.equipamentoDominio.estoqueEquipamento.dto.IEstoqueEquipamentoUpdateData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateEstoqueEquipamentoUseCaseTest {

    private EstoqueEquipamentoGateway estoqueGateway;
    private ColaboradorGateway colaboradorGateway;
    private UpdateEstoqueEquipamentoUseCase useCase;

    @BeforeEach
    void setup() {
        estoqueGateway = mock(EstoqueEquipamentoGateway.class);
        colaboradorGateway = mock(ColaboradorGateway.class);
        useCase = new UpdateEstoqueEquipamentoUseCase(estoqueGateway, colaboradorGateway);
    }

    @Test
    void deveAtualizarComSucesso() throws EstoqueEquipamentoNotFoundException, ColaboradorNotFoundException {
        UUID id = UUID.randomUUID();
        UUID colaboradorEntregadorId = UUID.randomUUID();
        UUID colaboradorResponsavelId = UUID.randomUUID();
        OffsetDateTime dataHoraColeta = OffsetDateTime.now();

        EstoqueEquipamento estoque = new EstoqueEquipamento();
        Colaborador entregador = new Colaborador();
        Colaborador responsavel = new Colaborador();

        IEstoqueEquipamentoUpdateData dados = mock(IEstoqueEquipamentoUpdateData.class);
        when(dados.colaboradorEntregadorId()).thenReturn(colaboradorEntregadorId);
        when(dados.colaboradorResponsavelId()).thenReturn(colaboradorResponsavelId);
        when(dados.dataHoraColeta()).thenReturn(dataHoraColeta);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoque));
        when(colaboradorGateway.findById(colaboradorEntregadorId)).thenReturn(Optional.of(entregador));
        when(colaboradorGateway.findById(colaboradorResponsavelId)).thenReturn(Optional.of(responsavel));
        when(estoqueGateway.update(any())).thenReturn(estoque);

        EstoqueEquipamento result = useCase.execute(id, dados);

        assertNotNull(result);
        verify(estoqueGateway).update(estoque);
    }

    @Test
    void deveLancarExcecao_QuandoEstoqueNaoEncontrado() {
        UUID id = UUID.randomUUID();
        IEstoqueEquipamentoUpdateData dados = mock(IEstoqueEquipamentoUpdateData.class);
        when(estoqueGateway.findById(id)).thenReturn(Optional.empty());

        assertThrows(EstoqueEquipamentoNotFoundException.class, () -> useCase.execute(id, dados));
    }

    @Test
    void deveLancarExcecao_QuandoColaboradorEntregadorNaoEncontrado() {
        UUID id = UUID.randomUUID();
        UUID colaboradorEntregadorId = UUID.randomUUID();

        EstoqueEquipamento estoque = new EstoqueEquipamento();
        IEstoqueEquipamentoUpdateData dados = mock(IEstoqueEquipamentoUpdateData.class);
        when(dados.colaboradorEntregadorId()).thenReturn(colaboradorEntregadorId);
        when(dados.colaboradorResponsavelId()).thenReturn(UUID.randomUUID());
        when(dados.dataHoraColeta()).thenReturn(OffsetDateTime.now());

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoque));
        when(colaboradorGateway.findById(colaboradorEntregadorId)).thenReturn(Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(id, dados));
    }

    @Test
    void deveLancarExcecao_QuandoColaboradorResponsavelNaoEncontrado() {
        UUID id = UUID.randomUUID();
        UUID colaboradorEntregadorId = UUID.randomUUID();
        UUID colaboradorResponsavelId = UUID.randomUUID();

        EstoqueEquipamento estoque = new EstoqueEquipamento();
        Colaborador entregador = new Colaborador();

        IEstoqueEquipamentoUpdateData dados = mock(IEstoqueEquipamentoUpdateData.class);
        when(dados.colaboradorEntregadorId()).thenReturn(colaboradorEntregadorId);
        when(dados.colaboradorResponsavelId()).thenReturn(colaboradorResponsavelId);
        when(dados.dataHoraColeta()).thenReturn(OffsetDateTime.now());

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoque));
        when(colaboradorGateway.findById(colaboradorEntregadorId)).thenReturn(Optional.of(entregador));
        when(colaboradorGateway.findById(colaboradorResponsavelId)).thenReturn(Optional.empty());

        assertThrows(ColaboradorNotFoundException.class, () -> useCase.execute(id, dados));
    }

    @Test
    void deveLancarExcecao_QuandoDataHoraColetaForNula() {
        UUID id = UUID.randomUUID();
        UUID colaboradorEntregadorId = UUID.randomUUID();
        UUID colaboradorResponsavelId = UUID.randomUUID();

        EstoqueEquipamento estoque = new EstoqueEquipamento();
        Colaborador entregador = new Colaborador();
        Colaborador responsavel = new Colaborador();

        IEstoqueEquipamentoUpdateData dados = mock(IEstoqueEquipamentoUpdateData.class);
        when(dados.colaboradorEntregadorId()).thenReturn(colaboradorEntregadorId);
        when(dados.colaboradorResponsavelId()).thenReturn(colaboradorResponsavelId);
        when(dados.dataHoraColeta()).thenReturn(null);

        when(estoqueGateway.findById(id)).thenReturn(Optional.of(estoque));
        when(colaboradorGateway.findById(colaboradorEntregadorId)).thenReturn(Optional.of(entregador));
        when(colaboradorGateway.findById(colaboradorResponsavelId)).thenReturn(Optional.of(responsavel));

        assertThrows(EstoqueEquipamentoNotFoundException.class, () -> useCase.execute(id, dados));
    }
}
