package com.fiap.hackaton.entity.estoqueInsumo.exception;

public class EstoqueInsumoNotFoundException extends Exception {

    public EstoqueInsumoNotFoundException() {
        super("Não tem o insumo no estoque!");
    }
}
