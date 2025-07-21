package com.fiap.hackaton.bdd.hospital;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class UpdateHospitalBDD {

    private Response response;
    private String hospitalId;
    private String hospitalJsonPut;

    @Dado("que existe um hospital cadastrado - Update")
    public void que_existe_um_hospital_cadastrado() {

        String hospitalJson = """
                {
                    "colaboradores": [],
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

        hospitalJsonPut = """
        {
            "cep": "12345628",
            "numero": 110,
            "quantidadeLeitoAtual": 11,
            "quantidadeLeitoMaximo": 25
        }
        """;

        hospitalId = response.jsonPath().getString("id");
    }

    @Quando("o hospital for modificado pelo ID - Update")
    public void o_hospital_for_modificado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJsonPut)
                .when()
                .put("/hospitais/" + hospitalId);
    }

    @Então("o hospital modificado será retornado no sistema - Update")
    public void o_hospital_modificado_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}
