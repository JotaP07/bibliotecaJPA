package com.app.service;

import com.app.entity.Editora;
import com.app.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    public String save(Editora editora) {
        editoraRepository.save(editora);
        return "Editora salva com sucesso!";
    }

    public void saveAll(List<Editora> editoras) {
        editoraRepository.saveAll(editoras);
    }

    public List<Editora> findAll() {
        return editoraRepository.findAll();
    }

    public Editora findById(long id) {
        return editoraRepository.findById(id).orElse(null);
    }

    public String update(Editora editora, long id) {
        if (editoraRepository.existsById(id)) {
            editora.setId(id);
            editoraRepository.save(editora);
            return "Editora atualizada com sucesso!";
        } else {
            return "Editora não encontrada!";
        }
    }

    public String delete(long id) {
        if (editoraRepository.existsById(id)) {
            editoraRepository.deleteById(id);
            return "Editora deletada com sucesso!";
        } else {
            return "Editora não encontrada!";
        }
    }

    public void deleteAll() {
        editoraRepository.deleteAll();
    }

    public List<Editora> findByNome(String nome) {
        return editoraRepository.findByNome(nome);
    }
}
