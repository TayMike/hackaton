package com.fiap.hackaton.bdd.insumoDominio.entregaInsumo;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class SearchEntregaInsumoBDD {

    private String hospitalId;
    private String colaboradorId;
    private String insumoId1;
    private String insumoId2;
    private Response response;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um hospital cadastrado para entrega de insumo - Search")
    public void que_existe_um_hospital_cadastrado() {
        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "16245678221301",
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
        hospitalId = responseHospital.jsonPath().getString("id");
    }

    @E("que existe um colaborador cadastrado para entrega de insumo - Search")
    public void que_existe_um_colaborador_cadastrado() {
        String colaboradorJson = String.format("""
            {
                "cpf": "19628833129",
                "nome": "Maria Souza",
                "matricula": "MKH456",
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
        colaboradorId = responseColaborador.jsonPath().getString("id");
    }

    @E("que existem insumos cadastrados para entrega - Search")
    public void que_existem_insumos_cadastrados() {
        String insumoJson1 = String.format("""
            {
              "nome": "Dipirona",
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
              "nome": "Paracetamol",
              "custo": 12.50,
              "quantidade": 100,
              "peso": 500,
              "validade": "%s",
              "marca": "FarmaciaCentral",
              "unidadeMedida": "MG"
            }
            """, dataFormatada);

        Response responseInsumos1 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson1)
                .when()
                .post("/insumos");
        responseInsumos1.then().statusCode(HttpStatus.CREATED.value());
        insumoId1 = responseInsumos1.jsonPath().getString("id");

        Response responseInsumos2 = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(insumoJson2)
                .when()
                .post("/insumos");
        responseInsumos2.then().statusCode(HttpStatus.CREATED.value());
        insumoId2 = responseInsumos2.jsonPath().getString("id");
    }

    @E("que foi construída uma entrega de insumos - Search")
    public void que_foi_construida_uma_entrega_de_insumos() {
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
        Response responseEntrega = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(entregaJson)
                .when()
                .post("/entrega-insumos");
        responseEntrega.then().statusCode(HttpStatus.CREATED.value());
        String entregaId = responseEntrega.jsonPath().getString("id");
    }

    @Quando("buscar entrega de insumo por id - Search")
    public void buscar_entrega_de_insumo_por_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/entrega-insumos");
    }

    @Então("deve retornar a entrega de insumo correspondente - Search")
    public void deve_retornar_a_entrega_de_insumo_correspondente() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}