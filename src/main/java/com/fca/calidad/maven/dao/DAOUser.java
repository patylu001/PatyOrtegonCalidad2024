package com.mayab.quality.loginunittest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.mayab.quality.loginunittest.model.User;

public class DAOUser implements IDAOUser {
	

		
		User findUserByEmail(String email) {
			return null;
		}
		
		int save(User u) {
			return 0;
		}
		
		List<User> findAll(){
			return null;
		}
		
		User findById(int id) {
			return 0;
		}
		
		User findById(id) {
			return null;
		}
		
		boolean deleteById(id){
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Connection getConnectionMySQL() {

		Connection con = null;
		try {
			// Establish the driver connector
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Set the URI for connecting the MySql database
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/calidad", "root", "123456");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	@Override
	public User findByUserName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int save(User user) {
		// TODO Auto-generated method stub
		Connection connection = getConnectionMySQL();
	int result = -1;
	try {
		// Declare statement query to run
		PreparedStatement preparedStatement;
		preparedStatement = connection
				.prepareStatement("insert INTO users(name, email, password, isLogged) values(?,?,?,?)");
		// Set the values to match in the ? query
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getEmail());
		preparedStatement.setString(3, user.getPassword());
		preparedStatement.setBoolean(4, user.isLogged());
		
		//Return the result of connection nad statement
		if (preparedStatement.executeUpdate() >= 1 ) {
			try(ResultSet rs = preparedStatement.getGeneratedKeys()) {
				if (rs.next()) {
					result = rs.getInt(1);
				}
			}
		}
		System.out.println("\n");
		System.out.println("Alumno anadido con exito");
		System.out.println(">> Return: " + result + "\n");
		// Close connection with the database
		connection.close();
		preparedStatement.close();
	} catch (Exception e) {
		System.out.println(e);
	}
	return result;
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User updateUser(User userOld) {
		// TODO Auto-generated method stub
		return null;
	}

}
