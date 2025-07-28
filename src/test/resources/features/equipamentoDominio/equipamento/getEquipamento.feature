#language: pt

Funcionalidade: Encontrar um equipamento

  Cenário: Encontrar um equipamento pelo ID
    Dado que existe um hospital e um equipamento cadastrados - Get
    Quando um equipamento for buscado pelo ID - Get
    Então o equipamento será retornado no sistema - Get