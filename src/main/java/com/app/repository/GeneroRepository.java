package com.app.repository;

import com.app.entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
    public List<Genero> findByNome(String nome);
}
