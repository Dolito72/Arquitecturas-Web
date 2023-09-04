package dao;

import interfaces.DAO;
import entities.Cliente;
import factory.MysqlDAOFactory;

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

import dto.DtoCliente;



public class DaoCliente implements DAO<Cliente> {
	private Connection conn;
	public DaoCliente(Connection conn){
		this.conn = conn;
	}

	@Override
	public void insert(Cliente c) throws SQLException {
			try {
				if(c.getEmail() == null || c.getNombre() == null || c.getIdCliente() == null) {
					throw new SQLException ("Debe ingresar un cliente valido, con todos sus atributos");
				}
				MysqlDAOFactory.getInstance().connect();
				Integer idCliente = c.getIdCliente();
				String nombre = c.getNombre();
				String email = c.getEmail();
				String insert = "INSERT INTO cliente  (idCliente, nombre, email) VALUES(?, ?, ?)  ";
				PreparedStatement ps = conn.prepareStatement(insert);
				ps.setInt(1, idCliente);
				ps.setString(2, nombre);
				ps.setString(3, email);
				ps.executeUpdate();
				//conectar.commit();
				ps.close();
				MysqlDAOFactory.getInstance().close();
			} catch(SQLException e) {
				System.out.println(e);
			}
				
	}
	
	
	public ArrayList<DtoCliente> mejoresClientes() throws SQLException{
		Connection conectar = MysqlDAOFactory.getInstance().connect();
		//Connection conectar = conn.connect();
		ArrayList<DtoCliente> clientes = new ArrayList<>();
		String select = "SELECT c.*, SUM(p.valor * fp.cantidad) as mejores_clientes FROM cliente c JOIN factura f ON c.idCliente = f.idCliente JOIN factura_producto fp ON fp.idFactura = f.idFactura JOIN producto p ON p.idProducto = fp.idProducto WHERE c.idCliente = f.idCliente GROUP BY c.idCliente ORDER BY mejores_clientes DESC;   ";
		PreparedStatement ps = conectar.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer idCliente = rs.getInt(1);
				String nombre = rs.getString(2);
				//String mail = rs.getString(3);
				Integer suma = rs.getInt(4);
				DtoCliente c = new DtoCliente(idCliente, nombre, suma);
				clientes.add(c);
			}
			MysqlDAOFactory.getInstance().close();
			return clientes;
	}

	@Override
	public Cliente get(long id) {
		Cliente cliente = new Cliente();
		try {
			MysqlDAOFactory.getInstance().connect();
			PreparedStatement get = conn.prepareStatement("SELECT * FROM cliente WHERE idCliente = ?");
			get.setLong(1, id);
			ResultSet consulta = get.executeQuery();
			
			if(consulta.next()) {
				
				cliente.setIdCliente(Integer.parseInt(consulta.getString("idCliente")));
				cliente.setNombre(consulta.getString("nombre"));
				cliente.setEmail(consulta.getString("email"));
			} else {
				throw new SQLException("no existe ese id");
				
			}
			
			MysqlDAOFactory.getInstance().close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		return cliente;  
	}

	@Override
	public List getAll() {
		List<Cliente> clientes = null;
		try {
			MysqlDAOFactory.getInstance().connect();
			PreparedStatement getAll = conn.prepareStatement("SELECT * FROM cliente");
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
			MysqlDAOFactory.getInstance().close();
			return clientes;
		}catch(SQLException e) {
			System.out.println(e);
		}
		return clientes;
	}


	@Override
	public void update(Cliente c, String[] params) {
		try {
			MysqlDAOFactory.getInstance().connect();
			PreparedStatement update = conn.prepareStatement("UPDATE cliente SET nombre = ?, email = ? WHERE idCliente = ?");
			update.setString(1, c.getNombre());
			update.setString(2, c.getEmail());
			update.setInt(3, c.getIdCliente());
			update.executeUpdate();
			MysqlDAOFactory.getInstance().close();
		}catch(SQLException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public void delete(Cliente c) {
		try {
			MysqlDAOFactory.getInstance().connect();
			PreparedStatement delete = conn.prepareStatement("DELETE FROM cliente WHERE idCliente = ?");
			delete.setInt(1, c.getIdCliente());
			delete.executeUpdate();
			MysqlDAOFactory.getInstance().close();
		}catch(SQLException e) {
			System.out.println(e);
		}	
	}
}
		


	

