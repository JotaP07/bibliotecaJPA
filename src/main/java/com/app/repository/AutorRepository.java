package com.app.repository;

import com.app.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    public List<Autor> findByNome(String nome);
}
