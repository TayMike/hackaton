# language: pt

Funcionalidade: Encontrar um Paciente

  Cenário: Encontrar um Paciente
    Dado que existe um paciente cadastrado - Get
    Quando um paciente for buscado pelo ID - Get
    Então o paciente será retornado no sistema - Get