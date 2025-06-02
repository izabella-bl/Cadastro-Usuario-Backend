Feature: Registro de Usuário

  Scenario: Registro com sucesso
    Given o payload de registro com nome "Maria", email "maria@email.com" e senha "123456"
    When enviar a requisição POST para "/api/usuarios/registro"
    Then a resposta deve conter status 200

  Scenario: Registro com senhas diferentes
    Given o payload de registro com nome "João", email "joao@email.com", senha "123456" e confirmação "654321"
    When enviar a requisição POST para "/api/usuarios/registro"
    Then a resposta deve conter status 400