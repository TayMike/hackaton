#language: pt

Funcionalidade: Cadastro de Entrega de Insumo

  Cenário: Criar uma nova entrega de insumo
    Dado que existe um hospital cadastrado para entrega de insumo - Create
    E que existe um colaborador cadastrado para entrega de insumo - Create
    E que existem insumos cadastrados para entrega - Create
    E que foi construída uma entrega de insumos - Create
    Quando a entrega de insumos for cadastrada - Create
    Então a entrega de insumos será salva no sistema - Create
    E a entrega de insumos deve estar no formato esperado - Create