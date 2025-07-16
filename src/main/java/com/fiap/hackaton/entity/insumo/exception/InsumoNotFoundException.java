package com.fiap.hackaton.entity.insumo.exception;

public class InsumoNotFoundException extends Exception {

    public InsumoNotFoundException() {
        super("O insumo não foi encontrado!");
    }
}
