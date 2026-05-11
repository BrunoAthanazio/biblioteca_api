package com.weg.biblioteca_api.service.emprestimo;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.biblioteca_api.model.Emprestimo;

@Service
public class EmprestimoServiceImpl implements EmprestimoService{

    @Override
    public Emprestimo save(Emprestimo emprestimo) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public List<Emprestimo> findAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Emprestimo findById(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void update(Emprestimo emprestimo) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void devolucao(Long id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'devolucao'");
    }

}
