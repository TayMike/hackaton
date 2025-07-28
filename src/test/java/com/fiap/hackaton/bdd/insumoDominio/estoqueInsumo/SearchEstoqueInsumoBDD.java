package com.fiap.hackaton.bdd.insumoDominio.estoqueInsumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class SearchEstoqueInsumoBDD {

    private Response response;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existem vários estoques de insumo cadastrados - Search")
    public void que_existem_varios_estoques_de_insumo_cadastrados() {

        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "25365678283501",
                "colaboradoresIds": [],
                "cep": "12345678",
                "numero": 100,
                "quantidadeLeitoAtual": 10,
                "quantidadeLeitoMaximo": 20
            }
            """;

        Response responseHospital = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        responseHospital.then().statusCode(HttpStatus.CREATED.value());
        String hospitalId = responseHospital.jsonPath().getString("id");

        String colaboradorJson = String.format("""
            {
                "cpf": "12363208982",
                "nome": "Maria Souza",
                "matricula": "MLM456",
                "primeiroDiaCadastro": "%s",
                "ultimoDiaCadastro": null,
                "cep": "12345000",
                "numeroCasa": 123,
                "hospitalId": "%s",
                "setor": "Recepção"
            }
            """, dataFormatada, hospitalId);

        Response responseColaborador = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorJson)
                .when()
                .post("/colaboradores");

        responseColaborador.then().statusCode(HttpStatus.CREATED.value());
        String colaboradorId = responseColaborador.jsonPath().getString("id");

        String insumoJson1 = String.format("""
            {
              "nome": "Dipir",
              "custo": 12.50,
              "quantidade": 100,
              "peso": 500,
              "validade": "%s",
              "marca": "FarmaciaCentral",
              "unidadeMedida": "MG"
            }
            """, dataFormatada);

        String insumoJson2 = String.format("""
            {
              "nome": "Paracetam",
              "custo": 12.50,
              "quantidade": 100,
              "peso": 500,
              "validade": "%s",
              "marca": "FarmaciaCentral",
              "unidadeMedida": "MG"
            }
            """, dataFormatada);

        Response responseInsumo1 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson1)
                .when()
                .post("/insumos");
        responseInsumo1.then().statusCode(HttpStatus.CREATED.value());
        String insumoId1 = responseInsumo1.jsonPath().getString("id");

        Response responseInsumo2 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson2)
                .when()
                .post("/insumos");
        responseInsumo2.then().statusCode(HttpStatus.CREATED.value());
        String insumoId2 = responseInsumo2.jsonPath().getString("id");

        String entregaJson = String.format("""
                {
                    "insumos": [
                        {"insumoId": "%s", "quantidade": 250},
                        {"insumoId": "%s", "quantidade": 500}
                    ],
                    "colaboradorRecebedorId": "%s",
                    "dataHoraRecebimento": "%s",
                    "hospitalId": "%s"
                }
                """, insumoId1, insumoId2, colaboradorId, dataFormatada, hospitalId);

        Response estoqueResponse = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(entregaJson)
                .when()
                .post("/entrega-insumos");

        estoqueResponse.then().statusCode(HttpStatus.CREATED.value());
    }

    @Quando("os estoques de insumo forem buscados - Search")
    public void os_estoques_de_insumo_forem_buscados() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/estoque-insumos");
    }

    @Então("os estoques de insumo serão retornados no sistema - Search")
    public void os_estoques_de_insumo_serao_retornados_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }

}