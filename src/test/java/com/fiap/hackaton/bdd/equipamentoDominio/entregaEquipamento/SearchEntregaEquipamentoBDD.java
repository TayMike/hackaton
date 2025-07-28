package com.fiap.hackaton.bdd.equipamentoDominio.entregaEquipamento;

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

public class SearchEntregaEquipamentoBDD {

    private String hospitalId;
    private String colaboradorId;
    private String equipamentoId;
    private Response response;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um hospital cadastrado para entrega de equipamento - Search")
    public void que_existe_um_hospital_cadastrado() {
        String hospitalJson = """
            {
                "nome": "Hospital Central",
                "cnpj": "65312238210501",
                "colaboradoresIds": [],
                "cep": "12345678",
                "numero": 105,
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

    @E("que existe um colaborador cadastrado para entrega de equipamento - Search")
    public void que_existe_um_colaborador_cadastrado() {
        String colaboradorJson = String.format("""
            {
                "cpf": "22306213290",
                "nome": "João Silva",
                "matricula": "MLA234",
                "primeiroDiaCadastro": "%s",
                "ultimoDiaCadastro": null,
                "cep": "000000",
                "numeroCasa": 0,
                "hospitalId": "%s",
                "setor": "UTI"
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

    @E("que existem equipamentos cadastrados para entrega - Search")
    public void que_existem_equipamentos_cadastrados() {
        String equipamentoJson1 = String.format("""
            {
                "nome": "Bomba de Luvas",
                "custo": 1500.00,
                "tempoGarantia": "%s",
                "proximaManutencaoPreventiva": "%s",
                "numeroSerie": "SN423456789",
                "marca": "MedTech",
                "hospitalId": "%s"
            }
            """, dataFormatada, dataFormatada, hospitalId);

        Response responseEquipe = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(equipamentoJson1)
                .when()
                .post("/equipamentos");
        responseEquipe.then().statusCode(HttpStatus.CREATED.value());
        equipamentoId = responseEquipe.jsonPath().getString("id");
    }

    @E("que foi construída uma entrega de equipamentos - Search")
    public void que_foi_construida_uma_entrega_de_equipamentos() {
        String entregaJson = String.format("""
                {
                    "equipamentoId": "%s",
                    "colaboradorRecebedorId": "%s",
                    "dataHoraRecebimento": "%s",
                    "hospitalId": "%s"
                }
                """, equipamentoId, colaboradorId, dataFormatada, hospitalId);

    }

    @Quando("buscar entrega de equipamento por id - Search")
    public void buscar_entrega_de_equipamento_por_id() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/entrega-equipamentos");
    }

    @Então("deve retornar a entrega de equipamento correspondente - Search")
    public void deve_retornar_a_entrega_de_equipamento_correspondente() {
        response.then().statusCode(HttpStatus.OK.value());
    }
}