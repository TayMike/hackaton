package com.fiap.hackaton.entity.equipamento.exception;

public class EquipamentoNotFoundException extends Exception {

    public EquipamentoNotFoundException() {
        super("O equipamento não foi encontrado!");
    }
}
