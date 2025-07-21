package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.colaborador.model.Colaborador;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "colaborador")
public class ColaboradorSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, name = "cpf")
    private String cpf;

    @Column(nullable = false, name = "nome")
    private String nome;

    @Column(unique = true, nullable = false, name = "matricula")
    private String matricula;

    @Column(nullable = false, name = "primeiro_dia_cadastro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private OffsetDateTime primeiroDiaCadastro;

    @Column(nullable = false, name = "cep")
    private String cep;

    @Column(nullable = false, name = "numero_casa")
    private Integer numeroCasa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    @Column(nullable = false, name = "setor")
    private String setor;

    @Column(nullable = false, name = "ativo")
    private Boolean ativo;

    public ColaboradorSchema(Colaborador colaborador, HospitalSchema hospital) {
        this.id = colaborador.getId();
        this.cpf = colaborador.getCpf();
        this.nome = colaborador.getNome();
        this.matricula = colaborador.getMatricula();
        this.primeiroDiaCadastro = colaborador.getPrimeiroDiaCadastro();
        this.cep = colaborador.getCep();
        this.numeroCasa = colaborador.getNumeroCasa();
        this.hospital = hospital;
        this.setor = colaborador.getSetor();
        this.ativo = colaborador.getAtivo();
    }

    public Colaborador toColaborador() {
        Colaborador colaborador = new Colaborador (
                this.getCpf(),
                this.getNome(),
                this.getMatricula(),
                this.getPrimeiroDiaCadastro(),
                this.getCep(),
                this.getNumeroCasa(),
                this.getHospital().toHospitalSemColaboradores(),
                this.getSetor(),
                this.getAtivo()
        );

        colaborador.setId(this.getId());
        return colaborador;
    }

}