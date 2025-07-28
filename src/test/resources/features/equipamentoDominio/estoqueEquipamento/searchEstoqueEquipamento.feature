#language: pt

Funcionalidade: Busca de estoques de equipamentos

  Cenário: Buscar todos os estoques de equipamentos cadastrados
    Dado que existem vários estoques de equipamentos cadastrados - Search
    Quando os estoques de equipamentos forem buscados - Search
    Então os estoques de equipamentos serão retornados no sistema - Search