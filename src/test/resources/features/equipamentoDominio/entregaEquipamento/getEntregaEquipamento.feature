#language: pt
Funcionalidade: Buscar uma entrega de equipamento

  Cenário: Buscar uma entrega de equipamento - Get
    Dado que existe um hospital cadastrado para entrega de equipamento - Get
    E que existe um colaborador cadastrado para entrega de equipamento - Get
    E que existem equipamentos cadastrados para entrega - Get
    E que foi construída uma entrega de equipamentos - Get
    Quando buscar entregas de equipamentos por hospital - Get
    Então deve retornar as entregas de equipamentos do hospital - Get