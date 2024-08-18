package com.app.repository;

import com.app.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("FROM Venda v WHERE v.total_valor > :total_valor")
    public  List<Venda> findByValorAcima(Double total_valor);


    @Query("FROM Venda v WHERE v.total_valor < :total_valor")
    public  List<Venda> findByValorAbaixo(Double total_valor);
}
