package com.fiap.hackaton.bdd.colaborador;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateColaboradorBDD {

    private Response response;
    private String colaboradorJson;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    private String hospitalId;

    @Dado("que existe um hospital cadastrado - Criar")
    public void que_existe_um_hospital_cadastrado() {
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

        hospitalId = response.jsonPath().getString("id");
    }

    @E("que um colaborador foi contratado - Criar")
    public void que_um_colaborador_foi_contratado() {
        Hospital hospitalID = new Hospital();
        hospitalID.setId(UUID.fromString(hospitalId));

        colaboradorJson = String.format("""
        {
            "cpf": "12345678901",
            "nome": "João Silva",
            "matricula": "MAT1234",
            "primeiroDiaCadastro": "%s",
            "cep": "01001000",
            "numeroCasa": 101,
            "hospital": "%s",
            "setor": "UTI",
            "ativo": true
        }
        """, dataFormatada, hospitalId);

    }

    @Quando("o colaborador for cadastrado - Criar")
    public void o_colaborador_for_cadastrado() {

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(colaboradorJson)
                .when()
                .post("/colaboradores");
    }

    @Então("o colaborador será salvo no sistema - Criar")
    public void o_colaborador_sera_salvo_no_sistema() {
        response.then().statusCode(HttpStatus.CREATED.value());
    }

    @E("o colaborador deve estar no formato esperado - Criar")
    public void o_colaborador_deve_estar_no_formato_esperado() {
        response.then().body(matchesJsonSchemaInClasspath("schemas/colaborador.schema.json"));
    }
}