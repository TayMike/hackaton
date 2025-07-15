package com.fiap.hackaton.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IPessoa {

    UUID id = null;
    String cpf = "";
    String nome = "";
    LocalDateTime primeiroDiaCadastro = null;
    String cep = "";
    Integer numeroCasa = 0;

}
