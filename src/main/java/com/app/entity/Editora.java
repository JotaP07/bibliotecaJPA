package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min = 3, message = "O nome deve conter mais de 3 caracteres.")
    private String nome;

    @NotNull(message = "O telefone não deve ser nulo.")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}", message = "Número de telefone inválido. O formato deve ser (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.")
    private String telefone;

    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ inválido. O formato deve ser XX.XXX.XXX/XXXX-XX")
    @NotBlank(message = "O CNPJ não dev ser nulo.")
    private  String cnpj;

    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("editora")
    private List<Item> itens;
}
