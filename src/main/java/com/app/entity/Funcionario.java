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
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode ser nulo.")
    @Size(min = 3, message = "O nome deve conter no minímo 3 caracteres.")
    private String nome;

    @NotBlank(message = "O login não deve ser nulo.")
    @Size(min = 5, message = "O login deve conter no mínimo 5 caracteres.")
    private String login;

    @NotBlank(message = "A senha não deve ser nula.")
    @Size(min = 8, message = "A senha deve conter no mínimo 8 caracteres.")
    private String senha;

    @NotNull(message = "A idade não pode nula.")
    @Min(value = 0, message = "A idade não deve ser negativa.")
    private Integer idade;

    @NotNull(message = "O CPF não deve ser nulo.")
    @CPF(message = "CPF Inválida. O fomato deve ser 123.456.789.09")
    private String cpf;

    @NotNull(message = "O CEP não deve ser nulo.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP Inválido. O formato deve ser 12345-678.")
    private String cep;

    @NotNull(message = "O telefone não deve ser nulo.")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Número de telefone inválido. O formato deve ser (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;

    @Email(message = "E-mail Inválido. O fomato deve ser exemplo@exemplo.com.")
    private String email;

    @NotBlank(message = "O endereço não pode ser nulo ou vazio.")
    private String endereco;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("funcionario")
    private List<Venda> vendas;
}

