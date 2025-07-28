package com.fiap.hackaton.bdd.core.colaborador;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class GetColaboradorBDD {

    private Response response;
    private String colaboradorId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um colaborador cadastrado em um hospital - Get")
    public void que_existe_um_colaborador_cadastrado_em_um_hospital() {
        String hospitalJson = """
    {
        "nome": "Hospital Central",
        "cnpj": "12345678230202",
        "colaboradoresIds": [],
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

        String colaboradorJson = String.format("""
                {
                    "cpf": "12346122321",
                    "nome": "João Silva",
                    "matricula": "MT1275",
                    "primeiroDiaCadastro": "%s",
                    "ultimoDiaCadastro": null,
                    "cep": "01001000",
                    "numeroCasa": 101,
                    "hospitalId": "%s",
                    "setor": "UTI"
                }
                """, dataFormatada, response.jsonPath().getString("id"));

        Response responseColaborador = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorJson)
                .when()
                .post("/colaboradores");

        colaboradorId = responseColaborador.jsonPath().getString("id");

    }

    @Quando("um colaborador for buscado pelo ID - Get")
    public void um_colaborador_for_buscado_pelo_ID() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/colaboradores/" + colaboradorId);
    }

    @Então("o colaborador será retornado no sistema - Get")
    public void o_registro_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}
