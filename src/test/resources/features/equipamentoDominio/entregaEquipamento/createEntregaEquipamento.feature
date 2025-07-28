#language: pt
Funcionalidade: Criar uma entrega de equipamento

  Cenário: Criar uma entrega de equipamento
    Dado que existe um hospital cadastrado para entrega de equipamento - Create
    E que existe um colaborador cadastrado para entrega de equipamento - Create
    E que existem equipamentos cadastrados para entrega - Create
    E que foi construída uma entrega de equipamentos - Create
    Quando a entrega de equipamentos for cadastrada - Create
    Então a entrega de equipamentos será salva no sistema - Create
    E a entrega de equipamentos deve estar no formato esperado - Create