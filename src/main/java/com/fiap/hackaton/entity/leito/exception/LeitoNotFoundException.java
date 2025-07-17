package com.fiap.hackaton.entity.leito.exception;

public class LeitoNotFoundException extends Exception {

    public LeitoNotFoundException() {
        super("O leito não foi encontrado!");
    }
}
