package com.weg.biblioteca_api.service.usuario;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.weg.biblioteca_api.model.Usuario;
import com.weg.biblioteca_api.repository.usuario.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario save(Usuario usuario) throws SQLException {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() throws SQLException {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) throws SQLException {
        return usuarioRepository.findById(id);
    }

    @Override
    public void update(Usuario usuario) throws SQLException {
        usuarioRepository.update(usuario);
    }

    @Override
    public void delete(Long id) throws SQLException {
        usuarioRepository.delete(id);
    }

}
