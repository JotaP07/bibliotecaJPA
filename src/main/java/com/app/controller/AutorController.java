package com.app.controller;

import com.app.entity.Autor;
import com.app.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Autor autor) {
        try {
            String mensagem = this.autorService.save(autor);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveAll")
    public ResponseEntity<String> saveAll(@RequestBody List<Autor> autores) {
        try {
            this.autorService.saveAll(autores);
            return new ResponseEntity<>("Autores salvos com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Autor>> findAll() {
        try {
            List<Autor> autores = autorService.findAll();
            return new ResponseEntity<>(autores, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Autor autor, @PathVariable long id){
        try {
            String mensagem = this.autorService.update(autor, id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro! " + e.getMessage(), HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findById/{idAutor}")
    public ResponseEntity<Autor> findById(@PathVariable long idAutor) {
        try {
            Autor autor = this.autorService.findById(idAutor);
            if (autor != null) {
                return new ResponseEntity<>(autor, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{idAutor}")
    public ResponseEntity<String> delete(@PathVariable long idAutor) {
        try {
            String mensagem = this.autorService.delete(idAutor);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deu erro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            this.autorService.deleteAll();
            return new ResponseEntity<>("Todos os autores foram deletados!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findByNome")
    public ResponseEntity<List<Autor>> findByNome(@RequestParam String nome) {
        try {
            List<Autor> lista = this.autorService.findByNome(nome);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
