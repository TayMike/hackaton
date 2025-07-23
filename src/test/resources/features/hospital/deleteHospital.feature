#language: pt

Funcionalidade: Deletar um hospital

  Cenário: Deletar um hospital - Delete
    Dado que existe um hospital cadastrado - Delete
    Quando o hospital for deletado pelo ID - Delete
    Então o hospital deletado não será retornado no sistema - Delete