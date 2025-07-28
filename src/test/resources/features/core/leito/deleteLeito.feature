# language: pt

Funcionalidade: Deletar um Leito

  Cenário: Deletar um Leito
    Dado que existe um leito cadastrado - Delete
    Quando o leito for deletado pelo ID - Delete
    Então o leito deletado não será retornado no sistema - Delete