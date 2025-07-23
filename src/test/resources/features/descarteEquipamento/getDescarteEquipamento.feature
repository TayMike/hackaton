#language: pt

Funcionalidade: Buscar um descarte de equipamento

  Cenário: Buscar um descarte de equipamento por ID
    Dado que existe um descarte de equipamento cadastrado em um hospital - Get
    Quando um descarte de equipamento for buscado pelo ID - Get
    Então o descarte de equipamento será retornado no sistema - Get