#language: pt

Funcionalidade: Encontrar um hospital

  Cenário: Encontrar um hospital - Get
    Dado que existe um hospital cadastrado - Get
    Quando um hospital for buscado pelo ID - Get
    Então o hospital será retornado no sistema - Get