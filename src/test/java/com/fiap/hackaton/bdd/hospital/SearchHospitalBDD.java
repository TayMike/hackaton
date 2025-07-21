package com.fiap.hackaton.bdd.hospital;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class SearchHospitalBDD {

    private Response response;

    @Dado("que existem vários hospitais cadastrados - Search")
    public void que_existem_varios_hospitais_cadastrados_em_um_hospital() {

        String hospitalJson = """
    {
        "hospitais": [],
        "cep": "12345678",
        "numero": 100,
        "quantidadeLeitoAtual": 10,
        "quantidadeLeitoMaximo": 20
    }
    """;

        Response response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        response.then().statusCode(201);
    }

    @Quando("os hospitais foram buscados pelo ID - Search")
    public void os_hospitais_foram_buscados_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/hospitais");
    }

    @Então("os hospitais serão retornados no sistema - Search")
    public void os_hospitais_serao_retornados_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}
