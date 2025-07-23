#language: pt

Funcionalidade: Listar estoques de insumo

  Cenário: Buscar todos os estoques de insumo
    Dado que existem vários estoques de insumo cadastrados - Search
    Quando os estoques de insumo forem buscados - Search
    Então os estoques de insumo serão retornados no sistema - Search