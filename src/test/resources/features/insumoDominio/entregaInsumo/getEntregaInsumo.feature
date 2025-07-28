#language: pt

Funcionalidade: Buscar entrega de insumo por ID

  Cenário: Buscar entrega de insumo - Get
    Dado que existe um hospital cadastrado para entrega de insumo - Get
    E que existe um colaborador cadastrado para entrega de insumo - Get
    E que existem insumos cadastrados para entrega - Get
    E que foi construída uma entrega de insumos - Get
    Quando buscar entregas de insumos por hospital - Get
    Então deve retornar as entregas de insumos do hospital - Get