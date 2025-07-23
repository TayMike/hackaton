#language: pt

Funcionalidade: Buscar todas as entregas de insumo

  Cenário: Buscar todas as entregas de insumo - Search
    Dado que existe um hospital cadastrado para entrega de insumo - Search
    E que existe um colaborador cadastrado para entrega de insumo - Search
    E que existem insumos cadastrados para entrega - Search
    E que foi construída uma entrega de insumos - Search
    Quando buscar entrega de insumo por id - Search
    Então deve retornar a entrega de insumo correspondente - Search