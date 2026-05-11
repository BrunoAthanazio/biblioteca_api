package com.weg.biblioteca_api.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.weg.biblioteca_api.model.Usuario;
import com.weg.biblioteca_api.service.usuario.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario postUsuario(@RequestBody Usuario usuario){
        try{
            return usuarioService.save(usuario);
        } catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public List<Usuario>  getUsuarios(){
        try{
            return usuarioService.findAll();
        } catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        try{
            return usuarioService.findById(id);
        } catch (SQLException | RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @PutMapping
    public void putUsuario(@RequestBody Usuario usuario){
        
    }
}
