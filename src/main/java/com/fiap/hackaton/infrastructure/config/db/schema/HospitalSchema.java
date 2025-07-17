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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Hospital")
public class HospitalSchema extends AbstractEntitySchema<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "cep")
    private String cep;

    @Column(nullable = false, name = "numero")
    private Integer numero;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ColaboradorSchema> colaboradores = new ArrayList<>();

    @Column(nullable = false, name = "quantidade_leito_atual")
    private Integer quantidadeLeitoAtual;

    @Column(nullable = false, name = "quantidade_leito_maximo")
    private Integer quantidadeLeitoMaximo;

    public HospitalSchema(Hospital hospital) {
        this.cep = hospital.getCep();
        this.numero = hospital.getNumero();
        this.colaboradores = hospital.getColaboradores().stream()
                .map(ColaboradorSchema::new)
                .collect(Collectors.toList());
        this.quantidadeLeitoAtual = hospital.getQuantidadeLeitoAtual();
        this.quantidadeLeitoMaximo = hospital.getQuantidadeLeitoMaximo();
    }

    public Hospital toHospital() {
        Hospital hospital = new Hospital(
                this.colaboradores.stream().map(ColaboradorSchema::toColaborador).collect(Collectors.toList()),
                this.cep,
                this.numero,
                this.quantidadeLeitoAtual,
                this.quantidadeLeitoMaximo
        );

        hospital.setId(this.getId());

        return hospital;
    }
}