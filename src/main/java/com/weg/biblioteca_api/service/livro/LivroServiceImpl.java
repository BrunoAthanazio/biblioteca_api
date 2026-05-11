package com.weg.biblioteca_api.service.livro;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.biblioteca_api.model.Livro;
import com.weg.biblioteca_api.repository.livro.LivroRepository;

@Service
public class LivroServiceImpl implements LivroService{

    private final LivroRepository livroRepository;

    public LivroServiceImpl(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    @Override
    public Livro save(Livro livro) throws SQLException {
        livroRepository.save(livro);
        return livro;
    }

    @Override
    public List<Livro> findAll() throws SQLException {
        return livroRepository.findAll();
    }

    @Override
    public Livro findById(Long id) throws SQLException {
        return livroRepository.findById(id);
    }

    @Override
    public void update(Livro livro) throws SQLException {
        livroRepository.update(livro);
    }

    @Override
    public void delete(Long id) throws SQLException {
        livroRepository.delete(id);
    }

}
