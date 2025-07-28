package com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception;

public class EstoqueInsumoNotFoundException extends Exception {

    public EstoqueInsumoNotFoundException(String id) {
        super(id);
    }
}
