package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import entities.Cliente;
import entities.Producto;
import interfaces.DAO;

public class DaoProducto implements DAO<Producto> {
	static Conexion conn = Conexion.getInstance();
	
	private static void createTable() {
		try {

			Connection conectar = conn.connect();
			String table = "CREATE TABLE producto(" + 
					"idProducto INT NOT NULL AUTO_INCREMENT," +
					"nombre VARCHAR(45)," +
					"valor FLOAT," +
					"PRIMARY KEY (idProducto))";
			conectar.prepareStatement(table).execute();
			
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	public static void insert(Connection conn, int idProducto, String nombre, Float valor) {
		
		String insert = "INSERT INTO producto (idCliente, nombre, valor) VALUES (?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(insert);
			ps.setInt(1, idProducto);
			ps.setString(2, nombre);
			ps.setFloat(3, valor);
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
		
	}

	public Producto get(long id) {
		return null;
	}

	public List<Producto> getAll() {
		return null;

	}

	public void save(Producto producto) {

	}

	public void update(Producto producto, String[] params) {

	}

	public void delete(Producto producto) {

	}

	private static void getAll(Connection conn, String producto) {
		String select = "SELECT * FROM " + producto;
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

	
	public static void main(String args[]) throws SQLException {
		DaoProducto producto = new DaoProducto();
		try {
			producto.createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
	

