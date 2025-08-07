package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.hackaton.entity.core.colaborador.model.Colaborador;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "colaborador")
public class ColaboradorSchema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "cpf", length = 11)
    @NotBlank(message = "CPF não pode estar vazio")
    @Size(min = 11, max = 11, message = "CPF deve ter exatamente 11 caracteres")
    @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas 11 dígitos numéricos")
    private String cpf;

    @Column(nullable = false, name = "nome", length = 100)
    @NotBlank(message = "Nome não pode estar vazio")
    @Size(max = 100, message = "Nome deve ser menor ou igual que 100 caracteres")
    private String nome;

    @Column(unique = true, nullable = false, name = "matricula", length = 50)
    @NotBlank(message = "Matrícula não pode estar vazia")
    @Size(max = 50, message = "Matrícula deve ser menor ou igual que 50 caracteres")
    private String matricula;

    @Column(nullable = false, name = "primeiro_dia_cadastro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private OffsetDateTime primeiroDiaCadastro;

    @Column(name = "ultimo_dia_cadastro")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private OffsetDateTime ultimoDiaCadastro;

    @Column(nullable = false, name = "cep", length = 8)
    @NotBlank(message = "CEP não pode estar vazio")
    @Size(min = 8, max = 8, message = "CEP deve ter exatamente 8 caracteres")
    @Pattern(regexp = "\\d{8}", message = "CEP deve conter apenas 8 dígitos numéricos")
    private String cep;

    @Column(nullable = false, name = "numero_casa")
    @NotNull(message = "Número da casa não pode ser nulo")
    @Min(value = 1, message = "Número da casa deve ser maior que 0")
    private Integer numeroCasa;

    @Column(name = "hospital_id", nullable = false)
    @NotNull(message = "Hospital ID não pode ser nulo")
    private UUID hospitalId;

    @Column(nullable = false, name = "setor", length = 50)
    @NotBlank(message = "Setor não pode estar vazio")
    @Size(max = 50, message = "Setor deve ser menor ou igual que 50 caracteres")
    private String setor;

    public ColaboradorSchema(Colaborador colaborador) {
        this.id = colaborador.getId();
        this.cpf = colaborador.getCpf();
        this.nome = colaborador.getNome();
        this.matricula = colaborador.getMatricula();
        this.primeiroDiaCadastro = colaborador.getPrimeiroDiaCadastro();
        this.ultimoDiaCadastro = colaborador.getUltimoDiaCadastro();
        this.cep = colaborador.getCep();
        this.numeroCasa = colaborador.getNumeroCasa();
        this.hospitalId = colaborador.getHospitalId();
        this.setor = colaborador.getSetor();
    }

    public Colaborador toEntity() {
            Colaborador colaborador = new Colaborador (
                    this.getCpf(),
                    this.getNome(),
                    this.getMatricula(),
                    this.getCep(),
                    this.getNumeroCasa(),
                    this.getHospitalId(),
                    this.getSetor(),
                    this.getPrimeiroDiaCadastro(),
                    this.getUltimoDiaCadastro()
            );

            colaborador.setId(this.getId());
            return colaborador;
        }

}