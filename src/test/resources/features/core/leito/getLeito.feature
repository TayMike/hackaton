# language: pt

Funcionalidade: Encontrar um Leito

  Cenário: Encontrar um Leito
    Dado que existe um leito cadastrado - Get
    Quando um leito for buscado pelo ID - Get
    Então o leito será retornado no sistema - Get