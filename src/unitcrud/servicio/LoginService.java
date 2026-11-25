package com.fca.calidad.unitcrud.servicio;

import com.fca.calidad.unitcrud.dao.IDAOUsuario;
import com.fca.calidad.unitcrud.modelo.Usuario;

public class LoginService {
    IDAOUsuario dao;

    public LoginService(IDAOUsuario d) {
        dao = d;
    }

    public boolean login(String email, String pass) {
        Usuario u = dao.findUserByEmail(email);
        if (u != null) {
            if (u.getPassword().equals(pass)) {

                return true;
            } else {

                return false;
            }
        } else {
            return false;
        }
    }
}
