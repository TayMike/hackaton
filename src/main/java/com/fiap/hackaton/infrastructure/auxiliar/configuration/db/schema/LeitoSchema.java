package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fiap.hackaton.entity.core.leito.model.Leito;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "leito",
        indexes = {
                @Index(name = "idx_leito_hospital_id", columnList = "hospital_id"),
                @Index(name = "idx_leito_paciente_id", columnList = "paciente_id"),
                @Index(name = "idx_leito_identificacao_hospital", columnList = "identificacao, hospital_id", unique = true)
        }
)
public class LeitoSchema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "identificacao", nullable = false, length = 50)
    @NotBlank(message = "Identificação não pode estar vazia")
    @Size(max = 50, message = "Identificação deve ter no máximo 50 caracteres")
    private String identificacao;

    @Column(name = "pavilhao", nullable = false, length = 50)
    @NotBlank(message = "Pavilhão não pode estar vazio")
    @Size(max = 50, message = "Pavilhão deve ter no máximo 50 caracteres")
    private String pavilhao;

    @Column(name = "quarto", nullable = false, length = 50)
    @NotBlank(message = "Quarto não pode estar vazio")
    @Size(max = 50, message = "Quarto deve ter no máximo 50 caracteres")
    private String quarto;

    @Column(name = "hospital_id", nullable = false)
    @NotNull(message = "Hospital ID não pode ser nulo")
    private UUID hospitalId;

    @Column(name = "paciente_id")
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
