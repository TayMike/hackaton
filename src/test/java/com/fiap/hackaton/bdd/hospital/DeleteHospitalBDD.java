package com.fiap.hackaton.bdd.hospital;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

public class DeleteHospitalBDD {

    private Response response;
    private String hospitalId;

    @Dado("que existe um hospital cadastrado - Delete")
    public void que_existe_um_hospital_cadastrado() {

        String hospitalJson = """
        {
            "nome": "Hospital Central",
            "cnpj": "12345678000402",
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

    @Quando("o hospital for deletado pelo ID - Delete")
    public void um_hospital_for_deletado_pelo_ID() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete("/hospitais/" + hospitalId);
    }

    @Então("o hospital deletado não será retornado no sistema - Delete")
    public void o_hospital_deletado_nao_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}
