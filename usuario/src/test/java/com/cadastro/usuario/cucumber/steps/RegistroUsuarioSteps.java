package com.cadastro.usuario.cucumber.steps;

import io.cucumber.java.pt.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegistroUsuarioSteps {

    private Map<String, Object> payload;
    private Response response;

    @Dado("o payload de registro com nome {string}, email {string} e senha {string}")
    public void criarPayloadComSenhaIguais(String nome, String email, String senha) {
        payload = new HashMap<>();
        payload.put("nome", nome);
        payload.put("email", email);
        payload.put("senha", senha);
        payload.put("confirmacaoSenha", senha);
    }

    @Dado("o payload de registro com nome {string}, email {string}, senha {string} e confirmação {string}")
    public void criarPayloadComSenhasDiferentes(String nome, String email, String senha, String confirmacao) {
        payload = new HashMap<>();
        payload.put("nome", nome);
        payload.put("email", email);
        payload.put("senha", senha);
        payload.put("confirmacaoSenha", confirmacao);
    }

    @Quando("enviar a requisição POST para {string}")
    public void enviarPost(String endpoint) {
        response = RestAssured
                .given()
                .baseUri("http://localhost:8099")
                .contentType("application/json")
                .body(payload)
                .when()
                .post(endpoint);
    }

    @Então("a resposta deve conter status {int}")
    public void verificarStatus(int statusCode) {
        assertThat(response.getStatusCode(), is(statusCode));
    }
}