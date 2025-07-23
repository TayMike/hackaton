#language: pt

Funcionalidade: Consulta de estoque de equipamento

  Cenário: Buscar estoque de equipamento pelo ID
    Dado que existe um estoque de equipamento cadastrado - Get
    Quando um estoque de equipamento for buscado pelo ID - Get
    Então o estoque de equipamento será retornado no sistema - Get