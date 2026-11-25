package com.fca.calidad.unitcrud.servicio;

import java.util.ArrayList;
import java.util.List;

import com.fca.calidad.unitcrud.dao.IDAOUsuario;
import com.fca.calidad.unitcrud.modelo.Usuario;

public class UsuarioServicio {

    private IDAOUsuario dao;

    public UsuarioServicio(IDAOUsuario dao) {
        this.dao = dao;
    }

    /**
     * Crea un usuario nuevo tras validar:
     *  - name no puede ser null ni vacío
     *  - email debe contener "@"
     *  - password debe tener entre 8 y 16 caracteres
     *  - el email no debe estar ya registrado
     *
     * @return el Usuario creado (con id asignado), el existente si ya había uno,
     *         o null si alguna validación falla
     */
    public Usuario createUser(String name, String email, String password) {

        // Validación: nombre no nulo ni vacío
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        // Validación: email debe contener "@"
        if (email == null || !email.contains("@")) {
            return null;
        }

        // Validación: longitud de password entre 8 y 16
        if (password == null || password.length() < 8 || password.length() > 16) {
            return null;
        }

        // Si el email ya existe retorna el usuario existente (sin duplicar)
        Usuario existente = dao.findUserByEmail(email);
        if (existente != null) {
            return existente;
        }

        // Persistir y asignar id generado por el DAO
        Usuario user = new Usuario(name, email, password);
        int id = dao.save(user);
        user.setId(id);
        return user;
    }

    /**
     * Retorna todos los usuarios; nunca retorna null (lista vacía si no hay datos).
     */
    public List<Usuario> findAllUsers() {
        List<Usuario> users = dao.findAll();
        return users != null ? users : new ArrayList<>();
    }

    public Usuario findUserByEmail(String email) {
        return dao.findUserByEmail(email);
    }

    public Usuario findUserById(int id) {
        return dao.findById(id);
    }

    /**
     * Actualiza nombre y/o password de un usuario existente.
     *  - Si el usuario no existe lanza IllegalArgumentException
     *  - Solo actualiza el nombre si el nuevo valor no es vacío
     *  - Solo actualiza la password si cumple la longitud (8–16)
     *
     * @return el Usuario actualizado
     * @throws IllegalArgumentException si el id no corresponde a ningún usuario
     */
    public Usuario updateUser(Usuario user) {
        Usuario userOld = dao.findById(user.getId());

        if (userOld == null) {
            throw new IllegalArgumentException(
                "No existe un usuario con id: " + user.getId());
        }

        // Actualizar nombre solo si el nuevo valor no es vacío
        if (user.getNombre() != null && !user.getNombre().trim().isEmpty()) {
            userOld.setNombre(user.getNombre());
        }

        // Actualizar password solo si cumple la política de longitud
        if (user.getPassword() != null
                && user.getPassword().length() >= 8
                && user.getPassword().length() <= 16) {
            userOld.setPassword(user.getPassword());
        }

        return dao.update(userOld);
    }

    /**
     * Elimina un usuario por id.
     *  - Si el usuario no existe retorna false sin llamar a deleteById en el DAO
     */
    public boolean deleteUser(int id) {
        Usuario user = dao.findById(id);
        if (user == null) {
            return false;
        }
        return dao.deleteById(id);
    }
}