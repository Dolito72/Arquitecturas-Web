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

	public DaoProducto(Connection conn) {
		this.conn = conn;
	}

	public void createTable() {
		try {
			Connection conectar = MysqlDAOFactory.getInstance().connect();
			String table = "CREATE TABLE IF NOT EXISTS producto(" + "idProducto INT," + "nombre VARCHAR(45),"
					+ "valor FLOAT," + "PRIMARY KEY (idProducto))";
			conectar.prepareStatement(table).execute();
			MysqlDAOFactory.getInstance().close();

		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	@Override
	public void insert(CSVParser datosT) throws SQLException {

		Connection conectar = MysqlDAOFactory.getInstance().connect();

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
		MysqlDAOFactory.getInstance().close();

	}

	@Override
	public Producto get(long id) {
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
