package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import entities.Factura;
import entities.Producto;
import interfaces.DAO;

public class DaoFactura implements DAO<Factura>{
static Conexion conn = Conexion.getInstance();
	

	
	private static void createTable() {
		try {
			Connection conectar = conn.connect();
<<<<<<< HEAD
			String table = "CREATE TABLE IF NOT EXISTS factura(" + 
					"idFactura INT," +
					"idCliente INT," +
=======
			String table = "CREATE TABLE factura(" + 
					"idFactura INT NOT NULL AUTO_INCREMENT," +
					"idCliente INT, " +
>>>>>>> e7c680545ad6114f13fbc25e26fd2e1c2c671c11
					"PRIMARY KEY (idFactura)," + 
					"FOREIGN KEY(idCliente)REFERENCES cliente(idCliente))";
			conectar.prepareStatement(table).execute();
			
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
				Connection conectar = conn.connect();
				for (CSVRecord row : datosT) {
					int idFactura = Integer.parseInt(row.get("idFactura"));
					int idCliente = Integer.parseInt(row.get("idCliente"));
					String insert = "INSERT INTO factura (idFactura, idCliente) VALUES( ?, ?)  ";
					PreparedStatement ps = conectar.prepareStatement(insert);
					ps.setInt(1, idFactura);
					ps.setInt(2, idCliente);
					ps.executeUpdate();
					ps.close();
				}
				this.conn.close();
	}
	
	public Producto productoConMasRecaudacion() {
		Producto producto = new Producto(null, null, null);
		try {
			Connection conectar = conn.connect();
			String select =
"SELECT p.idProducto, p.nombre, p.valor, SUM(fp.cantidad * p.valor) AS total_recaudado FROM producto p JOIN factura_producto fp ON p.idProducto = fp.idProducto GROUP BY p.idProducto, p.valor ORDER BY total_recaudado DESC LIMIT 1";
				PreparedStatement ps = conectar.prepareStatement(select);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Integer id = rs.getInt(1);
					String nombre = rs.getString(2);
					Float valor = rs.getFloat(3);
					producto.setIdProducto(id);
					producto.setNombre(nombre);
					producto.setValor(valor);
				}
			this.conn.close();
			}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return producto;
	}

	@Override
	public Factura get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Factura t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Factura t, String[] params) {
		// TODO Auto-generated method stub
	}


	@Override
	public void delete(Factura t) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	public static void main(String args[]) throws SQLException, FileNotFoundException, IOException {
		DaoFactura factura = new DaoFactura();
		 CSVParser datosFacturas = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/facturas.csv"));
		try {
			
			System.out.println(factura.productoConMasRecaudacion());
			//factura.createTable();
			//factura.insert(datosFacturas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
