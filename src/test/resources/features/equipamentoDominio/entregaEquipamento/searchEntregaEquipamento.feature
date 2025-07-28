#language: pt
Funcionalidade: Buscar todas as entregas de equipamento

  Cenário: Buscar todas as entregas de equipamento - Search
    Dado que existe um hospital cadastrado para entrega de equipamento - Search
    E que existe um colaborador cadastrado para entrega de equipamento - Search
    E que existem equipamentos cadastrados para entrega - Search
    E que foi construída uma entrega de equipamentos - Search
    Quando buscar entrega de equipamento por id - Search
    Então deve retornar a entrega de equipamento correspondente - Search