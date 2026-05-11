package com.weg.biblioteca_api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.weg.biblioteca_api.model.Livro;
import com.weg.biblioteca_api.service.livro.LivroServiceImpl;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroServiceImpl livroService;

    public LivroController(LivroServiceImpl livroService){
        this.livroService = livroService;
    }

    @PostMapping
    public Livro postLivro(@RequestBody Livro livro){
        try{
            livroService.save(livro);
            return livro;
        } catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Livro> getLivros(){
        try{
            return livroService.findAll();
        } catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Livro getLivro(@PathVariable Long id){
        try{
            return livroService.findById(id);
        } catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Livro putLivro(@RequestBody Livro livro){
        try{
            livroService.update(livro);
            return livro;
        } catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLivro(@PathVariable Long id){
        try{
            livroService.delete(id);
        } catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
