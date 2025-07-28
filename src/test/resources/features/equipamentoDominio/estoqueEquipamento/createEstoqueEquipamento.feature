#language: pt

Funcionalidade: Cadastro de estoque de equipamento

  Cenário: Criar um novo estoque de equipamento
    Dado que existe um hospital cadastrado para o estoque de equipamento - Create
    E que existem equipamentos cadastrados para o estoque - Create
    E que um colaborador foi o entregador - Criar
    E que um colaborador foi o responsavel - Criar
    E que foi construído um estoque de equipamento para criação - Create
    Quando o estoque de equipamento for cadastrado - Create
    Então o estoque de equipamento será salvo no sistema - Create
    E o estoque de equipamento deve estar no formato esperado - Create