package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.hospital.model.Hospital;
import com.fiap.hackaton.entity.leito.model.Leito;
import com.fiap.hackaton.entity.paciente.model.Paciente;
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalSchema hospital;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteSchema paciente;

    public LeitoSchema(Leito leito) {
        this.identificacao = leito.getIdentificacao();
        this.pavilhao = leito.getPavilhao();
        this.quarto = leito.getQuarto();
        this.hospital = new HospitalSchema(leito.getHospital());
        this.paciente = new PacienteSchema(leito.getPaciente());
    }

    public Leito toLeito() {
        Leito leito = new Leito(
                this.identificacao,
                this.pavilhao,
                this.quarto,
                this.hospital.toHospital(),
                this.paciente.toPaciente()
        );
        leito.setId(this.getId());
        return leito;
    }

}
