# language: pt

Funcionalidade: Criar um Leito

  Cenário: Criar um Leito
    Dado que foi criado um hospital cadastrado - Criar
    E que foi criado um paciente cadastrado - Criar
    E que foi construído um leito com hospital e paciente - Criar
    Quando o leito for cadastrado - Criar
    Então o leito será salvo no sistema - Criar
    E o leito deve estar no formato esperado - Criar