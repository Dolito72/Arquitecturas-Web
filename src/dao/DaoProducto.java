package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
	
	@Override
	public void insert(Producto p) {
		try {
			if(p.getIdProducto() == null || p.getNombre() == null || p.getValor() == null) {
				throw new SQLException ("Debe ingresar un cliente valido, con todos sus atributos");
			}
			Connection conectar = conn.connect();
			PreparedStatement insert = conectar.prepareStatement("INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)");
			
			insert.setInt(1, p.getIdProducto());
			insert.setString(2, p.getNombre());
			insert.setFloat(3, p.getValor());
			
			insert.executeUpdate();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	@Override
	public  Producto get(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void save(Producto t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Producto t, String[] params) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(Producto t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
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
	

