package com.app.controller;

import com.app.entity.Editora;
import com.app.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/editora")
public class EditoraController {

    @Autowired
    private EditoraService editoraService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Editora editora) {
        try {
            String mensagem = this.editoraService.save(editora);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<Editora> editoras) {
        try {
            this.editoraService.saveAll(editoras);
            return new ResponseEntity<>("Editoras salvas com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Editora>> findAll() {
        try {
            List<Editora> editoras = editoraService.findAll();
            return new ResponseEntity<>(editoras, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Editora editora, @PathVariable long id){
        try {
            String mensagem = this.editoraService.update(editora, id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro! " + e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findById/{idEditora}")
    public ResponseEntity<Editora> findById(@PathVariable long idEditora) {
        try {
            Editora editora = this.editoraService.findById(idEditora);
            if (editora != null) {
                return new ResponseEntity<>(editora, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idEditora}")
    public ResponseEntity<String> delete(@PathVariable long idEditora) {
        try {
            String mensagem = this.editoraService.delete(idEditora);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            this.editoraService.deleteAll();
            return new ResponseEntity<>("Todas as editoras foram deletadas!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<Editora>> findByNome(@RequestParam String nome) {
        try {
            List<Editora> lista = this.editoraService.findByNome(nome);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
