#language: pt

Funcionalidade: Deletar um equipamento

  Cenário: Deletar um equipamento pelo ID
    Dado que existe um equipamento cadastrados - Delete
    Quando o equipamento for deletado pelo ID - Delete
    Então o equipamento deletado não será retornado no sistema - Delete