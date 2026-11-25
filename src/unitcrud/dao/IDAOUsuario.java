package com.fca.calidad.unitcrud.dao;

import java.util.List;

import com.fca.calidad.unitcrud.modelo.Usuario;

public interface IDAOUsuario {
    Usuario findUserByEmail(String email);

    int save(Usuario u);

    List<Usuario> findAll();

    Usuario findById(int id);

    boolean deleteById(int id);

    Usuario update(Usuario u);
}
