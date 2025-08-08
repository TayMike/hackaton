package com.fiap.hackaton.infrastructure.auxiliar.configuration.db.schema;

import com.fiap.hackaton.entity.core.hospital.model.Hospital;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "hospital",
        indexes = {
                @Index(name = "idx_hospital_nome", columnList = "nome")
        }
)
public class HospitalSchema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Nome não pode estar vazio")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    @NotBlank(message = "CNPJ não pode estar vazio")
    @Size(min = 14, max = 14, message = "CNPJ deve ter 14 dígitos")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter apenas dígitos")
    private String cnpj;

    @Column(nullable = false, length = 8)
    @NotBlank(message = "CEP não pode estar vazio")
    @Size(min = 8, max = 8, message = "CEP deve ter 8 dígitos")
    @Pattern(regexp = "\\d{8}", message = "CEP deve conter apenas dígitos")
    private String cep;

    @Column(nullable = false)
    @NotNull(message = "Número não pode ser nulo")
    @Positive(message = "Número deve ser positivo")
    private Integer numero;

    @ElementCollection
    @CollectionTable(name = "hospital_colaboradores", joinColumns = @JoinColumn(name = "hospital_id"))
    @Column(name = "colaborador_id", nullable = false)
    private List<UUID> colaboradoresIds = new ArrayList<>();

    @Column(nullable = false, name = "quantidade_leito_atual")
    @NotNull(message = "Quantidade de leitos atuais não pode ser nula")
    @PositiveOrZero(message = "Quantidade de leitos atuais deve ser zero ou positiva")
    private Integer quantidadeLeitoAtual;

    @Column(nullable = false, name = "quantidade_leito_maximo")
    @NotNull(message = "Quantidade de leitos máximos não pode ser nula")
    @Positive(message = "Quantidade de leitos máximos deve ser positiva")
    private Integer quantidadeLeitoMaximo;

    public HospitalSchema(Hospital hospital) {
        this.id = hospital.getId();
        this.nome = hospital.getNome();
        this.cnpj = hospital.getCnpj();
        this.colaboradoresIds = hospital.getColaboradoresIds();
        this.cep = hospital.getCep();
        this.numero = hospital.getNumero();
        this.quantidadeLeitoAtual = hospital.getQuantidadeLeitoAtual();
        this.quantidadeLeitoMaximo = hospital.getQuantidadeLeitoMaximo();
    }

    public Hospital toEntity() {
        Hospital hospital = new Hospital(
                this.getNome(),
                this.getCnpj(),
                this.getColaboradoresIds(),
                this.getCep(),
                this.getNumero(),
                this.getQuantidadeLeitoAtual(),
                this.getQuantidadeLeitoMaximo()
        );
        hospital.setId(this.getId());
        return hospital;
    }

}