package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fiap.hackaton.entity.core.leito.model.Leito;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "leito")
public class LeitoSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "identificacao", nullable = false)
    private String identificacao;

    @Column(name = "pavilhao", nullable = false)
    private String pavilhao;

    @Column(name = "quarto", nullable = false)
    private String quarto;

    @Column(name = "hospital_id", nullable = false)
    private UUID hospitalId;

    @Column(name = "paciente_id", nullable = false)
    private UUID pacienteId;

    public LeitoSchema(Leito leito) {
        this.id = leito.getId();
        this.identificacao = leito.getIdentificacao();
        this.pavilhao = leito.getPavilhao();
        this.quarto = leito.getQuarto();
        this.hospitalId = leito.getHospitalId();
        this.pacienteId = leito.getPacienteId();
    }

    public Leito toEntity() {
        Leito leito = new Leito(
                this.identificacao,
                this.pavilhao,
                this.quarto,
                this.hospitalId,
                this.pacienteId
        );
        leito.setId(this.getId());
        return leito;
    }

}
