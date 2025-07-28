package com.fiap.hackaton.entity.core.colaborador.exception;

public class ColaboradorNotFoundException extends Exception {

    public ColaboradorNotFoundException(String id) {
        super(id);
    }
}
