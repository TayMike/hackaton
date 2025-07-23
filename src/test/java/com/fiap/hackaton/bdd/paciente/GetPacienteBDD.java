package com.fiap.hackaton.bdd.paciente;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static io.restassured.RestAssured.given;

public class GetPacienteBDD {

    private String pacienteId;
    private Response response;

    @Dado("que existe um paciente cadastrado - Get")
    public void que_existe_um_paciente_cadastrado_get() {
        String pacienteJson = """
                {
                    "cpf": "11223344556",
                    "nome": "Carlos Pereira",
                    "primeiroDiaCadastro": "2025-03-01T09:00:00Z",
                    "cep": "67890000",
                    "numeroCasa": 300
                }
                """;
        pacienteId = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pacienteJson)
                .when()
                .post("/pacientes")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .path("id");
    }

    @Quando("um paciente for buscado pelo ID - Get")
    public void um_paciente_for_buscado_pelo_id_get() {
        response = given()
                .when()
                .get("/pacientes/" + pacienteId);
    }

    @Então("o paciente será retornado no sistema - Get")
    public void o_paciente_sera_retornado_no_sistema_get() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}