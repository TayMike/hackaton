package com.fiap.hackaton.entity.insumoDominio.estoqueInsumo.exception;

public class InsufficientStockException extends Exception {

    public InsufficientStockException(String id) {
        super(id);
    }

}
