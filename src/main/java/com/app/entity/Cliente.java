package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser nulo.")
    @Pattern(regexp = "^(\\S+\\s+\\S+.*)$", message = "O nome deve conter pelo menos duas palavras.")
    private String nome;

    @NotNull(message = "A idade não pode ser nula.")
    @Min(value = 0, message = "A idade não deve ser negativa.")
    private Integer idade;

    @NotNull(message = "O CPF não deve ser nulo.")
    @CPF(message = "CPF Inválido. O formato deve ser 123.456.789.09")
    private String cpf;

    @NotNull(message = "O CEP não deve ser nulo.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP Inválido. O formato deve ser 12345-678.")
    private String cep;

    @NotNull(message = "O telefone não deve ser nulo.")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Número de telefone inválido. O formato deve ser (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;

    @Email(message = "E-mail Inválido. O formato deve ser exemplo@exemplo.com.")
    private String email;

    @NotBlank(message = "O endereço não pode ser nulo ou vazio.")
    private String endereco;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("cliente")
    private List<Venda> vendas;

}
