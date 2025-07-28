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

public class UpdateColaboradorBDD {

    private Response response;
    private String colaboradorId;
    private String colaboradorJsonPut;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    private final String dataFormatadaMaisUmDia = dataCadastro.plusDays(1).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um colaborador cadastrado - Update")
    public void que_existe_um_colaborador_cadastrado() {
        String hospitalJson = """
    {
        "nome": "Hospital Central",
        "cnpj": "12345678234304",
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

        String hospitalId = response.jsonPath().getString("id");

        String colaboradorJson = String.format("""
                {
                    "cpf": "14645678901",
                    "nome": "João Silva",
                    "matricula": "FAT1234",
                    "primeiroDiaCadastro": "%s",
                    "ultimoDiaCadastro": null,
                    "cep": "01001000",
                    "numeroCasa": 101,
                    "hospitalId": "%s",
                    "setor": "UTI"
                }
                """, dataFormatada, hospitalId);

        Response responseColaborador = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorJson)
                .when()
                .post("/colaboradores");

        colaboradorJsonPut = String.format("""
        {
            "nome": "Jones Silva",
            "cep": "01001001",
            "numeroCasa": 110,
            "hospitalId": "%s",
            "setor": "Enfermaria",
            "ultimoDiaCadastro": "%s"
        }
        """, hospitalId, dataFormatadaMaisUmDia);

        colaboradorId = responseColaborador.jsonPath().getString("id");
    }

    @Quando("o colaborador for modificado pelo ID - Update")
    public void o_colaborador_for_modificado_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorJsonPut)
                .when()
                .put("/colaboradores/" + colaboradorId);
    }

    @Então("o colaborador modificado será retornado no sistema - Update")
    public void o_colaborador_modificado_sera_retornado_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}