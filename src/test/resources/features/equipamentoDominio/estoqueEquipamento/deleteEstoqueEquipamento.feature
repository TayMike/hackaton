#language: pt

Funcionalidade: Exclusão de estoque de equipamento

  Cenário: Deletar estoque de equipamento pelo ID
    Dado que existe um estoque de equipamento cadastrado para delete - Delete
    Quando o estoque de equipamento for deletado pelo ID - Delete
    Então o estoque de equipamento deletado não será retornado no sistema - Delete