Feature: Login de Usuário

  Scenario: Login com credenciais válidas
    Given que o usuário com email "vitor@gmail.com" e senha "vitor1234" está registrado
    When o usuário envia uma requisição de login para "/api/usuarios/login"
    Then o sistema deve retornar um token JWT válido com status 200