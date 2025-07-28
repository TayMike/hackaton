#language: pt

Funcionalidade: Atualizar um equipamento

  Cenário: Atualizar um equipamento pelo ID
    Dado que existe um hospital e um equipamento cadastrados - Update
    Quando o equipamento for modificado pelo ID - Update
    Então o equipamento modificado será retornado no sistema - Update