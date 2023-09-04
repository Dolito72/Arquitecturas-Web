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
import factory.MysqlDAOFactory;

public class DaoFacturaProducto implements DAO<Factura_Producto> {
	private Connection conn;

	public DaoFacturaProducto(Connection conn) {
		this.conn = conn;
	}


	@Override
	public void insert(Factura_Producto fp) throws SQLException {
			
				MysqlDAOFactory.getInstance().connect();
				//Connection conectar = conn.connect();
				int idFactura = fp.getId_factura();
				int idProducto = fp.getId_producto();
				int cantidad = fp.getCantidad();
				String insert = "INSERT INTO factura_producto (idFactura, idProducto, cantidad) VALUES(?, ?, ?)  ";
				PreparedStatement ps = conn.prepareStatement(insert);
				ps.setInt(1, idFactura);
				ps.setInt(2, idProducto);
				ps.setInt(3, cantidad);
				ps.executeUpdate();
				ps.close();
				MysqlDAOFactory.getInstance().close();
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

	public void update(Factura_Producto t, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Factura_Producto t) {
		// TODO Auto-generated method stub

	}

}