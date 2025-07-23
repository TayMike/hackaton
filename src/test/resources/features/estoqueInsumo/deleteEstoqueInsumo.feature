#language: pt

Funcionalidade: Deletar um estoque de insumo

  Cenário: Deletar um estoque de insumo
    Dado que existe um estoque de insumo cadastrado - Delete
    Quando o estoque de insumo for deletado pelo ID - Delete
    Então o estoque de insumo deletado não será retornado no sistema - Delete