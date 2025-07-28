# language: pt

Funcionalidade: Encontrar um Insumo

  Cenário: Encontrar um Insumo - Get
    Dado que existe um insumo cadastrado - Get
    Quando um insumo for buscado pelo ID - Get
    Então o insumo será retornado no sistema - Get