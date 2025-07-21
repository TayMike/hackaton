package com.fiap.hackaton.bdd.hospital;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class GetHospitalBDD {

    private Response response;
    private String hospitalJson;
    private String hospitalId;

    @Dado("que existe um hospital cadastrado - Get")
    public void que_existe_um_hospital_cadastrado() {

        hospitalJson = """
        {
            "colaboradores": [],
            "cep": "12345678",
            "numero": 100,
            "quantidadeLeitoAtual": 10,
            "quantidadeLeitoMaximo": 20
        }
        """;

        Response responseColaborador = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        hospitalId = responseColaborador.jsonPath().getString("id");

    }

    @Quando("um hospital for buscado pelo ID - Get")
    public void um_hospital_for_buscado_pelo_ID() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/hospitais/" + hospitalId);
    }

    @Então("o hospital será retornado no sistema - Get")
    public void o_hospital_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}
