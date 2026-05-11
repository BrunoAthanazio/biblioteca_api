package com.weg.biblioteca_api.repository.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.weg.biblioteca_api.infrastructure.ConnectionFactory;
import com.weg.biblioteca_api.model.Usuario;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{

    @Override
    public Usuario save(Usuario usuario) throws SQLException {
        String command = """
                INSERT INTO usuario
                (nome, email)
                VALUES
                (?, ?)
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(command, 
                PreparedStatement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setId(rs.getLong(1));
            }
            return usuario;
        }
    }

    @Override
    public List<Usuario> findAll() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = """
                SELECT id, nome, email
                FROM usuario
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                usuarios.add(new Usuario(id, nome, email));
            }
            return usuarios;
        }
    }

    @Override
    public Usuario findById(Long id) throws SQLException {
        String query = """
                SELECT nome, email
                FROM usuario
                WHERE id = ?
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                return new Usuario(id, nome, email);
            }
            return null;
        }
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        String command = """
                UPDATE usuario
                SET nome = ?, email = ?
                WHERE id = ?
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setLong(3, usuario.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String command = """
                DELETE FROM usuario
                WHERE id = ?
                """;
        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(command)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

}
