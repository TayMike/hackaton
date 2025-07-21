package com.fiap.hackaton.bdd.colaborador;

import com.fiap.hackaton.infrastructure.config.db.repository.HospitalRepository;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;

public class GetColaboradorBDD {

    @Autowired
    private HospitalRepository hospitalRepository;
    private Response response;
    private String colaboradorJson;
    private String colaboradorId;
    private final OffsetDateTime dataCadastro = OffsetDateTime.now();
    private final String dataFormatada = dataCadastro.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    @Dado("que existe um colaborador cadastrado em um hospital - Get")
    public void que_existe_um_colaborador_cadastrado_em_um_hospital() {
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
