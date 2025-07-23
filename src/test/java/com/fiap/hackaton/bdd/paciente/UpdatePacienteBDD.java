package com.fiap.hackaton.bdd.paciente;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import static io.restassured.RestAssured.given;

public class UpdatePacienteBDD {

    private String pacienteId;
    private String pacienteJsonPut;
    private Response response;

    @Dado("que existe um paciente cadastrado - Update")
    public void que_existe_um_paciente_cadastrado_update() {
        String pacienteJson = """
                {
                    "cpf": "22334455667",
                    "nome": "Ana Lima",
                    "primeiroDiaCadastro": "2025-05-01T11:00:00Z",
                    "cep": "20000000",
                    "numeroCasa": 400
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
        pacienteJsonPut = """
            {
                "cpf": "22334455667",
                "nome": "Ana Lima Atualizada",
                "primeiroDiaCadastro": "2025-05-01T11:00:00Z",
                "cep": "20000001",
                "numeroCasa": 401
            }
            """;
    }

    @Quando("o paciente for modificado pelo ID - Update")
    public void o_paciente_for_modificado_pelo_id_update() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(pacienteJsonPut)
                .when()
                .put("/pacientes/" + pacienteId);
    }

    @Então("o paciente modificado será retornado no sistema - Update")
    public void o_paciente_modificado_sera_retornado_no_sistema_update() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}