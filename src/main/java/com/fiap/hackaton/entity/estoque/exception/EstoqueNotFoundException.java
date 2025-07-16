package com.fiap.hackaton.entity.estoque.exception;

public class EstoqueNotFoundException extends Exception {

    public EstoqueNotFoundException() {
        super("O estoque não foi encontrado!");
    }
}
