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


	public  void createTable() throws SQLException {
		MysqlDAOFactory.getInstance().connect();
			String table = "CREATE TABLE IF NOT EXISTS  cliente(" + 
					"idCliente INT," +
					"nombre VARCHAR(500)," +
					"email VARCHAR(150)," +
					"PRIMARY KEY (idCliente))";
			conn.prepareStatement(table).execute();
			conn.close();
	}
	
	@Override
	public void insert(CSVParser datosT) throws SQLException {
			//try {
				//if(c.getEmail() == null || c.getNombre() == null || c.getIdCliente() == null) {
			////		throw new SQLException ("Debe ingresar un cliente valido, con todos sus atributos");
			//	}
			MysqlDAOFactory.getInstance().connect();
			for (CSVRecord row : datosT) {
					int idCliente = Integer.parseInt(row.get("idCliente"));
					String nombre = row.get("nombre");
					String email = row.get("email");
					String insert = "INSERT INTO cliente  (idCliente, nombre, email) VALUES(?, ?, ?)  ";
					PreparedStatement ps = conn.prepareStatement(insert);
					ps.setInt(1, idCliente);
					ps.setString(2, nombre);
					ps.setString(3, email);
					ps.executeUpdate();
					//conectar.commit();
					ps.close();
				}
				this.conn.close();
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
			//Connection conectar = conn.connect();
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
			MysqlDAOFactory.getInstance().connect();
			//Connection conectar = conn.connect();
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
			MysqlDAOFactory.getInstance().connect();
			//Connection conectar = conn.connect();
			PreparedStatement update = conn.prepareStatement("UPDATE cliente SET nombre = ?, email = ? WHERE idCliente = ?");
			
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
			MysqlDAOFactory.getInstance().connect();
			//Connection conectar = conn.connect();
			PreparedStatement delete = conn.prepareStatement("DELETE FROM cliente WHERE idCliente = ?");
			delete.setInt(1, c.getIdCliente());
			delete.executeUpdate();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}	
	}
	
}
		
	/**public static void main (String args[]) throws SQLException, FileNotFoundException, IOException {
		DaoCliente cliente = new DaoCliente();
		CSVParser datosClientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/clientes.csv"));
		
		try {
			//cliente.createTable();
			//cliente.insert(datosClientes);
			System.out.println (cliente.mejoresClientes());
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	*/


	

