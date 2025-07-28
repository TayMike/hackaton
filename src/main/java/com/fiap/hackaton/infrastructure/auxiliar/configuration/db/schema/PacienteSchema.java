package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.core.paciente.model.Paciente;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "paciente",
        indexes = {
                @Index(name = "idx_paciente_nome", columnList = "nome"),
                @Index(name = "idx_paciente_cpf", columnList = "cpf", unique = true)
        }
)
public class PacienteSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 11)
    @NotBlank(message = "CPF não pode estar vazio")
    @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas dígitos")
    private String cpf;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Nome não pode estar vazio")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    @Column(name = "data_nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Past(message = "Data de nascimento deve estar no passado")
    private OffsetDateTime dataNascimento;

    @Column(name = "primeiro_dia_cadastro", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @NotNull(message = "Primeiro dia de cadastro não pode ser nulo")
    @PastOrPresent(message = "Primeiro dia de cadastro deve ser presente ou passado")
    private OffsetDateTime primeiroDiaCadastro;

    @Column(nullable = false, length = 8)
    @NotBlank(message = "CEP não pode estar vazio")
    @Size(min = 8, max = 8, message = "CEP deve ter 8 dígitos")
    @Pattern(regexp = "\\d{8}", message = "CEP deve conter apenas dígitos")
    private String cep;

    @Column(name = "numero_casa", nullable = false)
    @NotNull(message = "Número da casa não pode ser nulo")
    @Positive(message = "Número da casa deve ser positivo")
    private Integer numeroCasa;

    public PacienteSchema(Paciente paciente) {
        this.id = paciente.getId();
        this.cpf = paciente.getCpf();
        this.nome = paciente.getNome();
        this.primeiroDiaCadastro = paciente.getPrimeiroDiaCadastro();
        this.cep = paciente.getCep();
        this.numeroCasa = paciente.getNumeroCasa();
    }

    public Paciente toEntity() {
        Paciente paciente = new Paciente(
                this.cpf,
                this.nome,
                this.primeiroDiaCadastro,
                this.cep,
                this.numeroCasa
        );
        paciente.setId(this.getId());
        return paciente;
    }
}