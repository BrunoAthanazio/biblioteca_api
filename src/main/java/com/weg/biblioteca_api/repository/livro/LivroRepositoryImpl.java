package com.weg.biblioteca_api.repository.livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.biblioteca_api.infrastructure.ConnectionFactory;
import com.weg.biblioteca_api.model.Livro;


@Repository
public class LivroRepositoryImpl implements LivroRepository{

    @Override
    public Livro save(Livro livro) throws SQLException {
        String command = """
                INSERT INTO livro
                (titulo, autor, ano_publicacao)
                VALUES
                (?, ?, ?);
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(command, 
                PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno_publicacao());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                livro.setId(rs.getLong(1));
            }
            return livro;
        }
    }

    @Override
    public List<Livro> findAll() throws SQLException {
        List<Livro> livros = new ArrayList<>();
        String query = """
                SELECT id, titulo, autor, ano_publicacao
                FROM livro
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int ano_publicacao = rs.getInt("ano_publicacao");

                livros.add(new Livro(id, titulo, autor, ano_publicacao));
            }
            return livros;
        }
    }

    @Override
    public Livro findById(Long id) throws SQLException {
        String query = """
                SELECT id, titulo, autor, ano_publicacao
                FROM livro
                WHERE id = ?
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                int ano_publicacao = rs.getInt("ano_publicacao");
                return new Livro(id, titulo, autor, ano_publicacao);
            }
            return null;
        }
    }

    @Override
    public void update(Livro livro) throws SQLException {
        String command = """
                UPDATE livro
                SET titulo = ?, autor = ?, ano_publicacao = ?
                WHERE id = ?
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno_publicacao());
            stmt.setLong(4, livro.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String command = """
                DELETE FROM livro
                WHERE id = ?
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
