#language: pt

Funcionalidade: Buscar um estoque de insumo

  Cenário: Buscar um estoque de insumo por ID
    Dado que existe um estoque de insumo cadastrado - Get
    Quando o estoque de insumo for buscado pelo ID - Get
    Então o estoque de insumo será retornado no sistema - Get