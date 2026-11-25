package com.fca.calidad.unitcrud.modelo;

public class Usuario {

    private String nombre;
    private String email;
    private String password;
    private int id;

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Usuario(String nombre, String email, String password, int id) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public Usuario() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

}
