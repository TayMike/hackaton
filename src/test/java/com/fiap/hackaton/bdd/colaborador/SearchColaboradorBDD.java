package com.fiap.hackaton.bdd.colaborador;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class SearchColaboradorBDD {

    private Response response;
    private String colaboradorJson;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existem vários colaboradores cadastrados em um hospital - Search")
    public void que_existem_varios_colaboradores_cadastrados_em_um_hospital() {
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

        response.then().statusCode(201);

        colaboradorJson = String.format("""
        {
            "cpf": "12345678900",
            "nome": "João Silva",
            "matricula": "MAT123",
            "primeiroDiaCadastro": "%s",
            "cep": "01001000",
            "numeroCasa": 101,
            "hospital": "%s",
            "setor": "UTI",
            "ativo": true
        }
        """, dataFormatada, response.jsonPath().getString("id"));
    }

    @Quando("os colaboradores foram buscados pelo ID - Search")
    public void os_colaboradores_foram_buscados_pelo_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/colaboradores");
    }

    @Então("os colaboradores serão retornados no sistema - Search")
    public void os_colaboradores_serao_retornados_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}