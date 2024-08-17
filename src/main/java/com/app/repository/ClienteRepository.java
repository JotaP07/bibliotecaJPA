package com.app.repository;

import com.app.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public List<Cliente> findByNome(String nome);
}
