package com.fca.calidad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fca.calidad.modelo.User;




public class DAOUserSQLite implements IDAOUser {
	private static final String DRIVER_NAME = "com.sqlite.jdbc.Driver";
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\mmcan\\IdeaProjects\\capas2\\\\Login.db";
//mysql : "com.mysql.cj.jdbc.Driver"
    //mysql: jdbc:mysql://localhost:3307/calidad2024
	public Connection getConnectionMySQL() {

		Connection con = null;
		try {
			// Establish the driver connector
			//Class.forName(DRIVER_NAME);
			// Set the URI for connecting the MySql database
			con = DriverManager.getConnection("jdbc:sqlite:/Users/patriciaortegoncano/Documents/FCA/Agosto2024/db/users.db", "", "");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	@Override
	public User findByUserName(String name) {
		Connection connection = getConnectionMySQL();
		PreparedStatement preparedStatement;
		ResultSet rs;
		
		User result = null;

		try {
			// Declare statement query to run
			preparedStatement = connection.prepareStatement("SELECT * from users WHERE name = ?");
			// Set the values to match in the ? on query
			preparedStatement.setString(1, name);
			rs = preparedStatement.executeQuery();

			// Obtain the pointer to the data in generated table
			rs.next();

			int id = rs.getInt(1);
			String username  = rs.getString(2);
			String email = rs.getString(3);
			String password = rs.getString(4);
			int isLogged = rs.getInt(5);

			result = new User(username, password, email);
			result.setId(id);
			result.setLogged(isLogged == 1? true: false);

			// Return the values of the search
			System.out.println("\n");
			System.out.println("---Alumno---");
			System.out.println("ID: " + result.getId());
			System.out.println("Nombre: " + result.getName());
			System.out.println("Email: " + result.getEmail());
			System.out.println("Tipo: " + result.isLogged() + "\n");
			// Close connection with the database
			connection.close();
			rs.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
					
	}

	@Override
	public int save(User user) {
		Connection connection = getConnectionMySQL();
		int result = -1;
		try {
			// Declare statement query to run
			PreparedStatement preparedStatement;
			preparedStatement = connection
					.prepareStatement("insert INTO users(name,email,password,isLogged) values(?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
			// Set the values to match in the ? on query
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setInt(4, user.isLogged() ? 1 : 0);

			// Return the result of connection nad statement
			if (preparedStatement.executeUpdate() >= 1) {
				try(ResultSet rs = preparedStatement.getGeneratedKeys()){
					if (rs.next()) {
						result = rs.getInt(1);
					}
				}
			}
			System.out.println("\n");
			System.out.println("Alumno añadido con exito");
			System.out.println(">> Return: " + result + "\n");
			// Close connection with the database
			connection.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
	}

	@Override
	public User findUserByEmail(String email) {
		Connection connection = getConnectionMySQL();
		PreparedStatement preparedStatement;
		ResultSet rs;
		
		User result = null;

		try {
			// Declare statement query to run
			preparedStatement = connection.prepareStatement("SELECT * from users WHERE email = ?");
			// Set the values to match in the ? on query
			preparedStatement.setString(1, email);
			rs = preparedStatement.executeQuery();

			// Obtain the pointer to the data in generated table
			rs.next();

			int id = rs.getInt(1);
			String username  = rs.getString(2);
			String emailUser = rs.getString(3);
			String password = rs.getString(4);
			int isLogged = rs.getInt(5);

			result = new User(username, password, emailUser);
			result.setId(id);
			result.setLogged(isLogged == 1 ? true: false);

			// Return the values of the search
			System.out.println("\n");
			System.out.println("---Alumno---");
			System.out.println("ID: " + result.getId());
			System.out.println("Nombre: " + result.getName());
			System.out.println("Email: " + result.getEmail());
			System.out.println("Tipo: " + result.isLogged() + "\n");
			// Close connection with the database
			connection.close();
			rs.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
					
	}

	@Override
	public List<User> findAll() {
		Connection connection = getConnectionMySQL();
		  PreparedStatement preparedStatement;
		  ResultSet rs;
		  boolean result = false;

		  User retrieved = null;

		  List<User> listaAlumnos = new ArrayList<User>();
		  
		  try {
		   // Declare statement query to run
		   preparedStatement = connection.prepareStatement("SELECT * from users");
		   // Set the values to match in the ? on query
		   rs = preparedStatement.executeQuery();

		   // Obtain the pointer to the data in generated table
		   while (rs.next()) {

			   int id = rs.getInt(1);
			   String name = rs.getString(2);
			   String email = rs.getString(3);
			   String password = rs.getString(4);
			   int log = rs.getInt(5);		 
			   retrieved = new User(name, email,password);
			   retrieved.setId(id);
			   retrieved.setLogged(log == 1 ? true : false);
			   listaAlumnos.add(retrieved);
		   }
		   
			   connection.close();
			   rs.close();
			   preparedStatement.close();
	
			  } catch (Exception e) {
			   System.out.println(e);
			  }
			  return listaAlumnos;
		  
		}

	

	@Override
	public User findById(int id) {
		Connection connection = getConnectionMySQL();
		PreparedStatement preparedStatement;
		ResultSet rs;
		
		User result = null;

		try {
			// Declare statement query to run
			preparedStatement = connection.prepareStatement("SELECT * from users WHERE id = ?");
			// Set the values to match in the ? on query
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();

			// Obtain the pointer to the data in generated table
			rs.next();

			int idUser = rs.getInt(1);
			String username  = rs.getString(2);
			String email = rs.getString(3);
			String password = rs.getString(4);
			int isLogged = rs.getInt(5);

			result = new User(username, password, email);
			result.setId(id);
			result.setLogged(isLogged == 1 ? true : false);

			// Return the values of the search
			System.out.println("\n");
			System.out.println("---Alumno---");
			System.out.println("ID: " + result.getId());
			System.out.println("Nombre: " + result.getName());
			System.out.println("Email: " + result.getEmail());
			System.out.println("Tipo: " + result.isLogged() + "\n");
			// Close connection with the database
			connection.close();
			rs.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
					
	}

	@Override
	public boolean deleteById(int id) {
		Connection connection = getConnectionMySQL();
		boolean result = false;

		try {
			// Declare statement query to run
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("Delete from users WHERE id = ?");
			// Set the values to match in the ? on query
			preparedStatement.setInt(1, id);

			// Return the result of connection and statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = true;
			}
			System.out.println("\n");
			System.out.println("Alumno eliminado con exito");
			System.out.println(">> Return: " + result + "\n");
			// Close connection with the database
			connection.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
	
	}

	
	public User updateUser(User userNew) {
		Connection connection = getConnectionMySQL();
		User result = null;

		try {
			// Declare statement query to run
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("UPDATE users SET name = ?,password= ? WHERE id = ?");
			// Set the values to match in the ? on query
			preparedStatement.setString(1, userNew.getName());
			preparedStatement.setString(2, userNew.getPassword());
			preparedStatement.setInt(3, userNew.getId());
			// Return the result of connection and statement
			if (preparedStatement.executeUpdate() >= 1) {
				result = userNew;
			}
			System.out.println("\n");
			// Close connection with the database
			connection.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		// Return statement
		return result;
	
	}

	

	

	

	
	

}