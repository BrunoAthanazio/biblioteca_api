package com.weg.biblioteca_api.service.emprestimo;

import java.sql.SQLException;
import java.util.List;

import com.weg.biblioteca_api.model.Emprestimo;

public interface EmprestimoService {
    Emprestimo save(Emprestimo emprestimo) throws SQLException;
    
    List<Emprestimo> findAll() throws SQLException;

    Emprestimo findById(Long id) throws SQLException;

    void update(Emprestimo emprestimo) throws SQLException;

    void delete(Long id) throws SQLException;

    void devolucao(Long id) throws SQLException;
}
