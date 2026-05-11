package com.weg.biblioteca_api.service.livro;

import java.sql.SQLException;
import java.util.List;

import com.weg.biblioteca_api.model.Livro;

public interface LivroService {
    Livro save(Livro livro) throws SQLException;
    
    List<Livro> findAll() throws SQLException;

    Livro findById(Long id) throws SQLException;

    void update(Livro livro) throws SQLException;

    void delete(Long id) throws SQLException;
}
