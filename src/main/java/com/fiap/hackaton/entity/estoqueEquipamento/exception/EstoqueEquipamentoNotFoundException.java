package com.fiap.hackaton.entity.estoqueEquipamento.exception;

public class EstoqueEquipamentoNotFoundException extends Exception {

    public EstoqueEquipamentoNotFoundException() {
        super("Não tem o equipamento no estoque!");
    }

}
