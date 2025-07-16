package com.fiap.hackaton.infrastructure.config.db.schema;

import com.fiap.hackaton.entity.paciente.model.Paciente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Paciente")
public class PacienteSchema extends AbstractEntitySchema<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String nome;

    @Column(name = "primeiro_dia_cadastro", nullable = false)
    private LocalDateTime primeiroDiaCadastro;

    @Column(nullable = false)
    private String cep;

    @Column(name = "numero_casa", nullable = false)
    private Integer numeroCasa;

    public PacienteSchema(Paciente paciente) {
        this.id = paciente.getId();
        this.cpf = paciente.getCpf();
        this.nome = paciente.getNome();
        this.primeiroDiaCadastro = paciente.getPrimeiroDiaCadastro();
        this.cep = paciente.getCep();
        this.numeroCasa = paciente.getNumeroCasa();
    }

    public Paciente toPaciente() {
        Paciente paciente = new Paciente(
                this.getCpf(),
                this.getNome(),
                this.getPrimeiroDiaCadastro(),
                this.getCep(),
                this.getNumeroCasa()
        );
        paciente.setId(this.getId());
        return paciente;
    }
}