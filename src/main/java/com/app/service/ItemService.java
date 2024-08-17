package com.app.service;

import com.app.entity.Item;
import com.app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public String save(Item item) {
        itemRepository.save(item);
        return "Item salvo com sucesso!";
    }

    public void saveAll(List<Item> items) {
        itemRepository.saveAll(items);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public String update(Item item, long id) {
        if (itemRepository.existsById(id)) {
            item.setId(id);
            itemRepository.save(item);
            return "Item atualizado com sucesso!";
        } else {
            return "Item não encontrado!";
        }
    }

    public String delete(long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return "Item deletado com sucesso!";
        } else {
            return "Item não encontrado!";
        }
    }

    public void deleteAll() {
        itemRepository.deleteAll();
    }

    public List<Item> findByTitulo(String titulo) {
        return itemRepository.findByTitulo(titulo);
    }
}