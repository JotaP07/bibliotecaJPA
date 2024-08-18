package com.app.repository;

import com.app.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    public List<Funcionario> findByNome(String nome);
}
