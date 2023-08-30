package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import interfaces.DAO;
import entities.Factura_Producto;

public class DaoFacturaProducto implements DAO<Factura_Producto> {
static Conexion conn = Conexion.getInstance();
	
private static void createTable() throws SQLException {
	Connection conectar = conn.connect();
	String table = "CREATE TABLE IF NOT EXISTS factura_producto(" + "idFactura INT," + "idProducto INT,"
			+ "cantidad INT," + "FOREIGN KEY(idFactura)REFERENCES factura(idFactura),"
			+ "FOREIGN KEY(idProducto)REFERENCES producto(idProducto))";

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
					int idFactura = Integer.parseInt(row.get("idFactura"));
					int idProducto = Integer.parseInt(row.get("idProducto"));
					int cantidad = Integer.parseInt(row.get("cantidad"));
					String insert = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES(?, ?, ?)  ";
					PreparedStatement ps = conectar.prepareStatement(insert);
					ps.setInt(1, idFactura);
					ps.setInt(2, idProducto);
					ps.setInt(3, cantidad);
					ps.executeUpdate();
					ps.close();
				}
				this.conn.close();
	}
	
	@Override
	public Factura_Producto get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Factura_Producto t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Factura_Producto t, String[] params) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Factura_Producto t) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) throws SQLException, FileNotFoundException, IOException {
		DaoFacturaProducto factura_producto = new DaoFacturaProducto();
		 CSVParser datosFacturasProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/facturas-productos.csv"));
		try {
			factura_producto.createTable();
			factura_producto.insert(datosFacturasProductos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}