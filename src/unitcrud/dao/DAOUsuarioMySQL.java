package com.fca.calidad.unitcrud.dao;

import java.util.List;

import com.fca.calidad.unitcrud.modelo.Usuario;

public class DAOUsuarioMySQL implements IDAOUsuario {

    private Connection connection;

    public DAOUsuarioMySQL() {

        try {
            // Establish the driver connector
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Set the URI for connecting the MySql database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/calidadSoftware2024", "root",
                    "123456");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // -------------------------------------------------------
    // Método auxiliar: mapea un ResultSet a un objeto Usuario
    // -------------------------------------------------------
    private Usuario mapRow(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("id"));
    }

    // -------------------------------------------------------
    // findUserByEmail
    // -------------------------------------------------------
    @Override
    public Usuario findUserByEmail(String email) {
        String sql = "SELECT id, nombre, email, password FROM Usuario WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por email: " + email, e);
        }
        return null;
    }

    // -------------------------------------------------------
    // save — inserta y retorna el id generado por MySQL
    // -------------------------------------------------------
    @Override
    public int save(Usuario u) {
        String sql = "INSERT INTO Usuario (nombre, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar usuario: " + u.getEmail(), e);
        }
        throw new RuntimeException("No se generó id al guardar el usuario: " + u.getEmail());
    }

    // -------------------------------------------------------
    // findAll
    // -------------------------------------------------------
    @Override
    public List<Usuario> findAll() {
        String sql = "SELECT id, nombre, email, password FROM Usuario";
        List<Usuario> lista = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener todos los usuarios", e);
        }
        return lista;
    }

    // -------------------------------------------------------
    // findById
    // -------------------------------------------------------
    @Override
    public Usuario findById(int id) {
        String sql = "SELECT id, nombre, email, password FROM Usuario WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar usuario por id: " + id, e);
        }
        return null;
    }

    // -------------------------------------------------------
    // deleteById
    // -------------------------------------------------------
    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar usuario con id: " + id, e);
        }
    }

    // -------------------------------------------------------
    // update — actualiza nombre y password; retorna el usuario
    // -------------------------------------------------------
    @Override
    public Usuario update(Usuario u) {
        String sql = "UPDATE Usuario SET nombre = ?, password = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getPassword());
            ps.setInt(3, u.getId());
            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new RuntimeException("No se encontró usuario con id: " + u.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar usuario con id: " + u.getId(), e);
        }
        return findById(u.getId());
    }
}
