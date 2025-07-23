#language: pt

Funcionalidade: Encontrar um coleta equipamento

  Cenário: Encontrar um coleta equipamento
    Dado que existe um coleta equipamento cadastrado em um hospital - Get
    Quando um coleta equipamento for buscado pelo ID - Get
    Então o coleta equipamento será retornado no sistema - Get