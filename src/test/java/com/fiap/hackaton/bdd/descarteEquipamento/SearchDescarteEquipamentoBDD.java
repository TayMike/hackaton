package com.fiap.hackaton.bdd.descarteEquipamento;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class SearchDescarteEquipamentoBDD {

    private Response response;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existem vários descartes de equipamento cadastrados - Search")
    public void que_existem_varios_descartes_de_equipamento_cadastrados() {
        String hospitalJson = """
        {
            "nome": "Hospital Leste",
            "cnpj": "33345678000177",
            "colaboradores": [],
            "cep": "33333333",
            "numero": 300,
            "quantidadeLeitoAtual": 12,
            "quantidadeLeitoMaximo": 30
        }
        """;

        Response responseHospital = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(hospitalJson)
                .when()
                .post("/hospitais");

        responseHospital.then().statusCode(201);
        String hospital = responseHospital.jsonPath().getString("id");

        String colaboradorJson = String.format("""
        {
            "cpf": "99988877766",
            "nome": "Luciana Alves",
            "matricula": "MAT2222",
            "primeiroDiaCadastro": "%s",
            "cep": "44444444",
            "numeroCasa": 45,
            "hospital": "%s",
            "setor": "Enfermagem",
            "ativo": true
        }
        """, dataFormatada, hospital);

        Response responseColaborador = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorJson)
                .when()
                .post("/colaboradores");

        responseColaborador.then().statusCode(201);
        String colaborador = responseColaborador.jsonPath().getString("id");

        String equipamentoJson = String.format("""
        {
            "nome": "Bisturi Elétrico",
            "custo": 3000.00,
            "tempoGarantia": "%s",
            "proximaManutencaoPreventiva": "%s",
            "marca": "Cirurgic",
            "hospital": "%s"
        }
        """, dataFormatada, dataFormatada, hospital);

        Response responseEquipamento = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson)
                .when()
                .post("/equipamentos");

        responseEquipamento.then().statusCode(201);
        String equipamento = responseEquipamento.jsonPath().getString("id");

        String descarteJson = String.format("""
        {
            "equipamentos": ["%s"],
            "quantidade": [1],
            "colaboradorResponsavel": "%s",
            "dataHoraDescarte": "%s",
            "hospital": "%s"
        }
        """, equipamento, colaborador, dataFormatada, hospital);

        Response responseDescarte = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(descarteJson)
                .when()
                .post("/descarteEquipamentos");

        responseDescarte.then().statusCode(201);
    }

    @Quando("os descartes de equipamento forem buscados - Search")
    public void os_descartes_de_equipamento_forem_buscados() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/descarteEquipamentos");
    }

    @Então("os descartes de equipamento serão retornados no sistema - Search")
    public void os_descartes_de_equipamento_serao_retornados_no_sistema() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}