package com.app.service;

import com.app.entity.Cliente;
import com.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public String save(Cliente cliente) {
        clienteRepository.save(cliente);
        return "Cliente salvo com sucesso!";
    }

    public void saveAll(List<Cliente> clientes) {
        clienteRepository.saveAll(clientes);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public String update(Cliente cliente, long id) {
        if (clienteRepository.existsById(id)) {
            cliente.setId(id);
            clienteRepository.save(cliente);
            return "Cliente atualizado com sucesso!";
        } else {
            return "Cliente não encontrado!";
        }
    }

    public String delete(long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return "Cliente deletado com sucesso!";
        } else {
            return "Cliente não encontrado!";
        }
    }

    public void deleteAll() {
        clienteRepository.deleteAll();
    }

    public List<Cliente> findByNome(String nome) {
        return clienteRepository.findByNome(nome);
    }
}
