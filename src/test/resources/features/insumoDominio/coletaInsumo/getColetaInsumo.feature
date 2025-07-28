#language: pt

Funcionalidade: Encontrar um coleta insumo

  Cenário: Encontrar um coleta insumo
    Dado que existe um coleta insumo cadastrado em um hospital - Get
    Quando um coleta insumo for buscado pelo ID - Get
    Então o coleta insumo será retornado no sistema - Get