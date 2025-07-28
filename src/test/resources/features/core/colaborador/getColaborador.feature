#language: pt

Funcionalidade: Encontrar um colaborador

  Cenário: Encontrar um colaborador
    Dado que existe um colaborador cadastrado em um hospital - Get
    Quando um colaborador for buscado pelo ID - Get
    Então o colaborador será retornado no sistema - Get