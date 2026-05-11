package com.weg.biblioteca_api.repository.emprestimo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.biblioteca_api.infrastructure.ConnectionFactory;
import com.weg.biblioteca_api.model.Emprestimo;

@Repository
public class EmprestimoRepositoryImpl implements EmprestimoRepository{

    @Override
    public Emprestimo save(Emprestimo emprestimo) throws SQLException {
        String command = """
                INSERT INTO emprestimo
                (livro_id, usuario_id, data_emprestimo)
                VALUES
                (?, ?, ?)
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(command, 
                PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setLong(1, emprestimo.getLivro_id());
            stmt.setLong(2, emprestimo.getUsuario_id());
            stmt.setObject(3, emprestimo.getData_emprestimo());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                emprestimo.setId(rs.getLong(1));
            }
            return emprestimo;
        }
    }

    @Override
    public List<Emprestimo> findAll() throws SQLException {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String query = """
                SELECT id, 
                    livro_id, 
                    usuario_id, 
                    data_emprestimo, 
                    data_devolucao
                FROM emprestimo
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long livro_id = rs.getLong("livro_id");
                Long usuario_id = rs.getLong("usuario_id");
                Date data_emprestimo = rs.getDate("data_emprestimo");
                Date data_devolucao = rs.getDate("data_devolucao");

                emprestimos.add(new Emprestimo(id, livro_id, usuario_id, data_emprestimo, data_devolucao));
            }
            return emprestimos;
        }
    }

    @Override
    public Emprestimo findById(Long id) throws SQLException {
        String query = """
                SELECT id, 
                    livro_id, 
                    usuario_id, 
                    data_emprestimo, 
                    data_devolucao
                FROM emprestimo
                WHERE id = ?
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Long livro_id = rs.getLong("livro_id");
                Long usuario_id = rs.getLong("usuario_id");
                Date data_emprestimo = rs.getDate("data_emprestimo");
                Date data_devolucao = rs.getDate("data_devolucao");

                return new Emprestimo(id, livro_id, usuario_id, data_emprestimo, data_devolucao);
            }
            return null;
        }
    }

    @Override
    public void update(Emprestimo emprestimo) throws SQLException {
        String command = """
                UPDATE emprestimo
                SET livro_id = ?, 
                    usuario_id = ?, 
                    data_emprestimo = ?, 
                    data_devolucao = ?
                WHERE id = ?;
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, emprestimo.getLivro_id());
            stmt.setLong(2, emprestimo.getUsuario_id());
            stmt.setDate(3, emprestimo.getData_emprestimo());
            stmt.setDate(4, emprestimo.getData_devolucao());
            stmt.setLong(5, emprestimo.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String command = """
                DELETE FROM emprestimo
                WHERE id = ?
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

}
