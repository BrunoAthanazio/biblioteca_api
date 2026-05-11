package com.weg.biblioteca_api.repository.emprestimo;

import java.sql.SQLException;
import java.util.List;

import com.weg.biblioteca_api.model.Emprestimo;

public interface EmprestimoRepository {
    Emprestimo save(Emprestimo emprestimo) throws SQLException;
    
    List<Emprestimo> findAll() throws SQLException;

    Emprestimo findById(Long id) throws SQLException;

    void update(Emprestimo emprestimo) throws SQLException;

    void delete(Long id) throws SQLException;
}
