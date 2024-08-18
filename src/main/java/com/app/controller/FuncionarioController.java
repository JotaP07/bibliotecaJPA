package com.app.controller;

import com.app.entity.Funcionario;
import com.app.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Funcionario funcionario) {
        try {
            String mensagem = this.funcionarioService.save(funcionario);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<Funcionario> funcionarios) {
        try {
            this.funcionarioService.saveAll(funcionarios);
            return new ResponseEntity<>("Funcionarios salvos com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Funcionario>> findAll() {
        try {
            List<Funcionario> funcionarios = funcionarioService.findAll();
            return new ResponseEntity<>(funcionarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Funcionario Funcionario, @PathVariable long id){
        try {
            String mensagem = this.funcionarioService.update(Funcionario, id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro! " + e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findById/{idFuncionario}")
    public ResponseEntity<Funcionario> findById(@PathVariable long idFuncionario) {
        try {
            Funcionario funcionario = this.funcionarioService.findById(idFuncionario);
            if (funcionario != null) {
                return new ResponseEntity<>(funcionario, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idFuncionario}")
    public ResponseEntity<String> delete(@PathVariable long idFuncionario) {
        try {
            String mensagem = this.funcionarioService.delete(idFuncionario);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            this.funcionarioService.deleteAll();
            return new ResponseEntity<>("Todos os Funcionarios foram deletados!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<Funcionario>> findByNome(@RequestParam String nome) {
        try {
            List<Funcionario> lista = this.funcionarioService.findByNome(nome);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
