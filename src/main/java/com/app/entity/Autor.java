package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do autor não deve ser nulo.")
    @Size(min = 2,message = " O nome deve conter no mínimo 2 caracteres ")
    private String nome;

    private String descricao;

    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("autores")
    private List<Item> itens;
}
