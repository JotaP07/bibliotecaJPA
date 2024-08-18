package com.app.service;

import com.app.entity.Funcionario;
import com.app.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {


    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public String save(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
        return "Funcionario salvo com sucesso!";
    }

    public void saveAll(List<Funcionario> funcionarios) {
        funcionarioRepository.saveAll(funcionarios);
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario findById(long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }

    public String update(Funcionario funcionario, long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionario.setId(id);
            funcionarioRepository.save(funcionario);
            return "Funcionario atualizado com sucesso!";
        } else {
            return "Funcionario não encontrado!";
        }
    }

    public String delete(long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return "Funcionario deletado com sucesso!";
        } else {
            return "Funcionario não encontrado!";
        }
    }

    public void deleteAll() {
        funcionarioRepository.deleteAll();
    }

    public List<Funcionario> findByNome(String nome) {
        return funcionarioRepository.findByNome(nome);
    }
    
    
}
