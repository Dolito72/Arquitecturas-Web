package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entities.Cliente;
import factory.MysqlDAOFactory;

public class Helper {

	public Helper(){
		
	}
	/*private Connection conn;
	
	
	public Helper() throws SQLException {
		this.conn = MysqlDAOFactory.getInstance().connect();
	}*/

	public void createTables() throws SQLException {
		this.createTableCliente();
		this.createTableFactura();
		this.createTableProducto();
		this.createTableFacturaProducto();
		
	}
	private void createTable(String sql) throws SQLException {
		Connection c =MysqlDAOFactory.getInstance().connect();
		String table = sql;
		c.prepareStatement(table).execute();
		MysqlDAOFactory.getInstance().close();
		
	}
	
	private  void createTableCliente() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS  cliente(" + 
					"idCliente INT," +
					"nombre VARCHAR(500)," +
					"email VARCHAR(150)," +
					"PRIMARY KEY (idCliente))";
		this.createTable(sql);
	}
	
	private void createTableFactura() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS factura(" + 
				"idFactura INT," +
				"idCliente INT," +
				"PRIMARY KEY (idFactura)," + 
				"FOREIGN KEY(idCliente)REFERENCES cliente(idCliente))";
		this.createTable(sql);
		
	}
	private void createTableFacturaProducto() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS factura_producto(" + "idFactura INT," + "idProducto INT,"
				+ "cantidad INT," + "FOREIGN KEY(idFactura)REFERENCES factura(idFactura),"
				+ "FOREIGN KEY(idProducto)REFERENCES producto(idProducto))";
		this.createTable(sql);
	}
	private void createTableProducto() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS producto(" + 
				"idProducto INT," +
				"nombre VARCHAR(45)," +
				"valor FLOAT," +
				"PRIMARY KEY (idProducto))";
		this.createTable(sql);
	}
	
	
	public void fillTables(CSVParser datosFacturas, CSVParser datosFactProd, CSVParser datosProd, CSVParser datosClientes) throws SQLException {
		this.fillTableCliente(datosClientes);
		this.fillTableFactura(datosFacturas);
		this.fillTableProducto(datosProd);
		this.fillTableFacturaProducto(datosFactProd);
	}
	
	private void fillTableProducto(CSVParser datosProd) throws SQLException {

		Connection c = MysqlDAOFactory.getInstance().connect();
		for (CSVRecord row : datosProd) {
			int idProducto = Integer.parseInt(row.get("idProducto"));
			String nombre = row.get("nombre");
			Float valor = Float.parseFloat(row.get("valor"));
			String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES(?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(insert);
			ps.setInt(1, idProducto);
			ps.setString(2, nombre);
			ps.setFloat(3, valor);
			ps.executeUpdate();
			ps.close();
		}
		MysqlDAOFactory.getInstance().close();
		
	}

	private void fillTableFacturaProducto(CSVParser datosFactProd) throws SQLException {
		Connection c = MysqlDAOFactory.getInstance().connect();
		for (CSVRecord row : datosFactProd) {
			int idFactura = Integer.parseInt(row.get("idFactura"));
			int idProducto = Integer.parseInt(row.get("idProducto"));
			int cantidad = Integer.parseInt(row.get("cantidad"));
			String insert = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES(?, ?, ?)  ";
			PreparedStatement ps = c.prepareStatement(insert);
			ps.setInt(1, idFactura);
			ps.setInt(2, idProducto);
			ps.setInt(3, cantidad);
			ps.executeUpdate();
			ps.close();
		}
		MysqlDAOFactory.getInstance().close();		
	}

	private void fillTableFactura(CSVParser datosT) throws SQLException {
		Connection c = MysqlDAOFactory.getInstance().connect();
		for (CSVRecord row : datosT) {
			int idFactura = Integer.parseInt(row.get("idFactura"));
			int idCliente = Integer.parseInt(row.get("idCliente"));
			String insert = "INSERT INTO factura (idFactura, idCliente) VALUES( ?, ?)  ";
			PreparedStatement ps = c.prepareStatement(insert);
			ps.setInt(1, idFactura);
			ps.setInt(2, idCliente);
			ps.executeUpdate();
			ps.close();
		}
		MysqlDAOFactory.getInstance().close();
		
	}

	private void fillTableCliente(CSVParser datosT) throws SQLException {
	
		Connection c = MysqlDAOFactory.getInstance().connect();
		for (CSVRecord row : datosT) {
				int idCliente = Integer.parseInt(row.get("idCliente"));
				String nombre = row.get("nombre");
				String email = row.get("email");
				String insert = "INSERT INTO cliente  (idCliente, nombre, email) VALUES(?, ?, ?)  ";
				PreparedStatement ps = c.prepareStatement(insert);
				ps.setInt(1, idCliente);
				ps.setString(2, nombre);
				ps.setString(3, email);
				ps.executeUpdate();
				//conectar.commit();
				ps.close();
			}
		MysqlDAOFactory.getInstance().close();
		}
	
	
	
}
