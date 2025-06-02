package com.cadastro.usuario.cucumber.steps;

import io.cucumber.java.pt.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoginSteps {

    private final String baseUrl = "http://localhost:8099/api/usuarios";
    private Response response;
    private String email;
    private String senha;

    @Dado("que o usuário com email {string} e senha {string} está registrado")
    public void que_o_usuário_com_email_e_senha_está_registrado(String email, String senha) {
        this.email = email;
        this.senha = senha;

        Map<String, String> body = new HashMap<>();
        body.put("nome", "Teste");
        body.put("email", email);
        body.put("senha", senha);
        body.put("confirmacaoSenha", senha);

        RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .when()
                .post(baseUrl + "/registro")
                .then()
                .statusCode(Matchers.anyOf(Matchers.is(200), Matchers.is(400))); // 400 se já estiver cadastrado
    }

    @Quando("o usuário envia uma requisição de login para {string}")
    public void o_usuário_envia_uma_requisição_de_login_para(String endpoint) {
        Map<String, String> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("senha", senha);

        response = RestAssured
                .given()
                .contentType("application/json")
                .body(loginData)
                .when()
                .post("http://localhost:8099" + endpoint);
    }

    @Então("o sistema deve retornar um token JWT válido com status {int}")
    public void o_sistema_deve_retornar_um_token_jwt_válido_com_status(Integer statusCode) {
        assertThat(response.getStatusCode(), Matchers.is(statusCode));
        assertThat(response.jsonPath().getString("token"), Matchers.notNullValue());
    }
}