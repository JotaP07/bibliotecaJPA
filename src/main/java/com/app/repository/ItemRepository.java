package com.app.repository;

import com.app.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByTitulo(String titulo);
}
