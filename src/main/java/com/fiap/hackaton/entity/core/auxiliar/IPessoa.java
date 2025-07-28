package com.fiap.hackaton.entity.core.auxiliar;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface IPessoa {

    UUID id = null;
    String cpf = "";
    String nome = "";
    OffsetDateTime primeiroDiaCadastro = null;
    String cep = "";
    Integer numeroCasa = 0;

}
