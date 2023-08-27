package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.Cliente;
import interfaces.DAO;

public class DaoCliente implements DAO<Cliente> {
	static Conexion conn = Conexion.getInstance();
	
	private static void createTable() {
		try {

			Connection conectar = conn.connect();
			String table = "CREATE TABLE cliente(" + 
					"idCliente INT NOT NULL AUTO_INCREMENT," +
					"nombre VARCHAR(500)," +
					"email VARCHAR(150)," +
					"PRIMARY KEY (idCliente))";
			conectar.prepareStatement(table).execute();
			
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	public static void insert(Connection conn, int idCliente, String nombre, String email) {
		
		String insert = "INSERT INTO persona (idCliente, nombre, email) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, idCliente);
			ps.setString(2, nombre);
			ps.setString(3, email);
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
	}

	public Cliente get(long id) {
		return null;
	}

	public List<Cliente> getAll() {
		return null;

	}

	public void save(Cliente cliente) {

	}

	public void update(Cliente cliente, String[] params) {

	}

	public void delete(Cliente cliente) {

	}

	private static void getAll(Connection conn, String cliente) {
		String select = "SELECT * FROM " + cliente;
		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + ".- " + rs.getString(2) + " " + rs.getString(3));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static void main (String args[]) throws SQLException {
		DaoCliente cliente = new DaoCliente();
		try {
		cliente.createTable();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	
	}
}
	

