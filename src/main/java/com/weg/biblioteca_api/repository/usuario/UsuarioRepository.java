package com.weg.biblioteca_api.repository.usuario;

import java.sql.SQLException;
import java.util.List;

import com.weg.biblioteca_api.model.Usuario;

public interface UsuarioRepository {
    Usuario save(Usuario usuario) throws SQLException;
    
    List<Usuario> findAll() throws SQLException;

    Usuario findById(Long id) throws SQLException;

    void update(Usuario usuario) throws SQLException;

    void delete(Long id) throws SQLException;
}
