package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entities.Cliente;
import entities.Producto;
import factory.MysqlDAOFactory;
import interfaces.DAO;

public class DaoProducto implements DAO<Producto> {
	private Connection conn;
	public DaoProducto(Connection conn){
		this.conn = conn;
	}
	
	public void createTable() {
		try {
			MysqlDAOFactory.getInstance().connect();
			//Connection conectar = conn.connect();
			String table = "CREATE TABLE IF NOT EXISTS producto(" + 
					"idProducto INT," +
					"nombre VARCHAR(45)," +
					"valor FLOAT," +
					"PRIMARY KEY (idProducto))";
			conn.prepareStatement(table).execute();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	@Override
	public void insert(CSVParser datosT) throws SQLException {
			//try {
				//if(c.getEmail() == null || c.getNombre() == null || c.getIdCliente() == null) {
			////		throw new SQLException ("Debe ingresar un cliente valido, con todos sus atributos");
			//	}
				Connection conectar = MysqlDAOFactory.getInstance().connect();
				//Connection conectar = conn.connect();
				for (CSVRecord row : datosT) {
					int idProducto = Integer.parseInt(row.get("idProducto"));
					String nombre = row.get("nombre");
					Float valor = Float.parseFloat(row.get("valor"));
					String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES(?, ?, ?)";
					PreparedStatement ps = conectar.prepareStatement(insert);
					ps.setInt(1, idProducto);
					ps.setString(2, nombre);
					ps.setFloat(3, valor);
					ps.executeUpdate();
					ps.close();
				}
				conectar.close();
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
}
	
/*	public static void main(String args[]) throws SQLException, FileNotFoundException, IOException {
		DaoProducto producto = new DaoProducto();
		 CSVParser datosProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/productos.csv"));
		try {
			producto.createTable();
			producto.insert(datosProductos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	

*/