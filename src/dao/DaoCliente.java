package dao;

import interfaces.DAO;
import entities.Cliente;

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
import java.util.ArrayList;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;



public class DaoCliente implements DAO<Cliente> {
	static Conexion conn = Conexion.getInstance();
	
	private static void createTable() throws SQLException {
		Connection conectar = conn.connect();
			String table = "CREATE TABLE IF NOT EXISTS  cliente(" + 
					"idCliente INT," +
					"nombre VARCHAR(500)," +
					"email VARCHAR(150)," +
					"PRIMARY KEY (idCliente))";
			conectar.prepareStatement(table).execute();
			conn.close();
	}
	
	@Override
	public void insert(CSVParser datosT) throws SQLException {
			//try {
				//if(c.getEmail() == null || c.getNombre() == null || c.getIdCliente() == null) {
			////		throw new SQLException ("Debe ingresar un cliente valido, con todos sus atributos");
			//	}
				Connection conectar = conn.connect();
				for (CSVRecord row : datosT) {
					int idCliente = Integer.parseInt(row.get("idCliente"));
					String nombre = row.get("nombre");
					String email = row.get("email");
					String insert = "INSERT INTO cliente  (idCliente, nombre, email) VALUES(?, ?, ?)  ";
					PreparedStatement ps = conectar.prepareStatement(insert);
					ps.setInt(1, idCliente);
					ps.setString(2, nombre);
					ps.setString(3, email);
					ps.executeUpdate();
					//conectar.commit();
					ps.close();
				}
				this.conn.close();
	}
	
	
	@Override
	public Cliente get(long id) {
		Cliente cliente = new Cliente();
		try {
			
			Connection conectar = conn.connect();
			PreparedStatement get = conectar.prepareStatement("SELECT * FROM cliente WHERE idCliente = ?");
			
			get.setLong(1, id);
			ResultSet consulta = get.executeQuery();
			
			if(consulta.next()) {
				
				cliente.setIdCliente(Integer.parseInt(consulta.getString("idCliente")));
				cliente.setNombre(consulta.getString("nombre"));
				cliente.setEmail(consulta.getString("email"));
			} else {
				throw new SQLException("no existe ese id");
				
			}
			
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		return cliente; //ver como no retornar nada cuando el id no existe 
	}

	@Override
	public List getAll() {
		List<Cliente> clientes = null;
		try {
			
			Connection conectar = conn.connect();
			PreparedStatement getAll = conectar.prepareStatement("SELECT * FROM cliente");
			ResultSet consulta = getAll.executeQuery();
			Cliente c = null;
			clientes = new ArrayList<>();
			
			
			while(consulta.next()) {
				c = new Cliente();
				c.setIdCliente(Integer.parseInt(consulta.getString("idCliente")));
				c.setNombre(consulta.getString("nombre"));
				c.setEmail(consulta.getString("email"));
				clientes.add(c);
			} 
			conn.close();
			return clientes;
		}catch(SQLException e) {
			System.out.println(e);
		}
		return clientes;
	}


	@Override
	public void save(Cliente t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Cliente c, String[] params) {
		try {
			Connection conectar = conn.connect();
			PreparedStatement update = conectar.prepareStatement("UPDATE cliente SET nombre = ?, email = ? WHERE idCliente = ?");
			
			update.setString(1, c.getNombre());
			update.setString(2, c.getEmail());
			update.setInt(3, c.getIdCliente());
			
			update.executeUpdate();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void delete(Cliente c) {
		try {
			Connection conectar = conn.connect();
			PreparedStatement delete = conectar.prepareStatement("DELETE FROM cliente WHERE idCliente = ?");
			delete.setInt(1, c.getIdCliente());
			delete.executeUpdate();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}	
	}
		
	public static void main (String args[]) throws SQLException, FileNotFoundException, IOException {
		DaoCliente cliente = new DaoCliente();
		CSVParser datosClientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/clientes.csv"));
		
		try {
			cliente.createTable();
			cliente.insert(datosClientes);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

}
	

