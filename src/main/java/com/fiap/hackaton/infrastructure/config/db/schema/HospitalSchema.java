package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "hospital")
@Getter
@Setter
@NoArgsConstructor
public class HospitalSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private Integer numero;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ColaboradorSchema> colaboradores = new ArrayList<>();

    @Column(nullable = false, name = "quantidade_leito_atual")
    private Integer quantidadeLeitoAtual;

    @Column(nullable = false, name = "quantidade_leito_maximo")
    private Integer quantidadeLeitoMaximo;

    public HospitalSchema(Hospital hospital) {
        this.id = hospital.getId();
        this.cep = hospital.getCep();
        this.numero = hospital.getNumero();
        this.quantidadeLeitoAtual = hospital.getQuantidadeLeitoAtual();
        this.quantidadeLeitoMaximo = hospital.getQuantidadeLeitoMaximo();
    }

    public Hospital toHospital() {
        Hospital hospital = new Hospital(
                this.getColaboradores().stream().map(ColaboradorSchema::toColaborador).collect(Collectors.toList()),
                this.getCep(),
                this.getNumero(),
                this.getQuantidadeLeitoAtual(),
                this.getQuantidadeLeitoMaximo()
        );
        hospital.setId(this.getId());
        return hospital;
    }

    public Hospital toHospitalSemColaboradores() {
        Hospital hospital = new Hospital(
                new ArrayList<>(),
                this.getCep(),
                this.getNumero(),
                this.getQuantidadeLeitoAtual(),
                this.getQuantidadeLeitoMaximo()
        );
        hospital.setId(this.getId());
        return hospital;
    }

}