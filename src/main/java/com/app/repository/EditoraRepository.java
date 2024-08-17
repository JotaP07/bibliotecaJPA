package com.app.repository;

import com.app.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
    public List<Editora> findByNome(String nome);
}
