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
    @Size(min = 3, message = "O nome deve conter no minímo 3 caracteres.")
    private String nome;

    @NotNull(message = "A idade não pode nula.")
    @Min(value = 0, message = "A idade não deve ser negativa.")
    private Integer idade;

    @NotNull(message = "O CPF não deve ser nulo.")
    @CPF(message = "CPF Inválida. O fomato deve ser 123.456.789.01")
    private String cpf;

    @NotNull(message = "O CEP não deve ser nulo.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP Inválido. O formato deve ser 12345-678.")
    private String cep;

    @NotNull(message = "O telefone não deve ser nulo.")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Número de telefone inválido. O formato deve ser (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;

    @Email(message = "E-mail Inválido. O fomato deve ser exemplo@exemplo.com.")
    private String email;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("cliente")
    private List<Venda> vendas;

}
