package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O tipo não pode ser nulo ou vazio.")
    @Size(min = 3, max = 50, message = "O tipo deve ter entre 3 e 50 caracteres.")
    private String tipo;

    @NotBlank(message = "O título não pode ser nulo ou vazio.")
    @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres.")
    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id")
    @JsonIgnoreProperties("itens")
    private Genero genero;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_autor",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    @JsonIgnoreProperties("itens")
    private List<Autor> autores;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "editora_id")
    @JsonIgnoreProperties("itens")
    private Editora editora;

    @NotBlank(message = "O ano de publicação não pode ser nulo ou vazio.")
    @Size(min = 3, max = 4, message = "O ano de publicação deve ter exatamente 4 dígitos.")
    private String anoPublicacao;
}
