package com.app.service;

import com.app.entity.Genero;
import com.app.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    public String save(Genero genero) {
        generoRepository.save(genero);
        return "Gênero salvo com sucesso!";
    }

    public void saveAll(List<Genero> generos) {
        generoRepository.saveAll(generos);
    }

    public List<Genero> findAll() {
        return generoRepository.findAll();
    }

    public Genero findById(long id) {
        return generoRepository.findById(id).orElse(null);
    }

    public String update(Genero genero, long id) {
        if (generoRepository.existsById(id)) {
            genero.setId(id);
            generoRepository.save(genero);
            return "Gênero atualizado com sucesso!";
        } else {
            return "Gênero não encontrado!";
        }
    }

    public String delete(long id) {
        if (generoRepository.existsById(id)) {
            generoRepository.deleteById(id);
            return "Gênero deletado com sucesso!";
        } else {
            return "Gênero não encontrado!";
        }
    }

    public void deleteAll() {
        generoRepository.deleteAll();
    }

    public List<Genero> findByNome(String nome) {
        return generoRepository.findByNome(nome);
    }
}