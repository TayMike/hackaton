# language: pt

Funcionalidade: Deletar um Insumo

  Cenário: Deletar um Insumo - Delete
    Dado que existe um insumo cadastrado - Delete
    Quando o insumo for deletado pelo ID - Delete
    Então o insumo deletado não será retornado no sistema - Delete