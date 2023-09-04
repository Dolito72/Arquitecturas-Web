package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.List;
import entities.Producto;
import factory.MysqlDAOFactory;
import interfaces.DAO;

public class DaoProducto implements DAO<Producto> {
	private Connection conn;

	public DaoProducto(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Producto p) throws SQLException {

		if(p.getIdProducto() == null || p.getNombre() == null || p.getValor() == null) {
			throw new SQLException ("Debe ingresar un producto valido, con todos sus atributos");
		}
		MysqlDAOFactory.getInstance().connect();
		int idProducto = p.getIdProducto();
		String nombre = p.getNombre();
		Float valor = p.getValor();
		String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES(?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, idProducto);
		ps.setString(2, nombre);
		ps.setFloat(3, valor);
		ps.executeUpdate();
		MysqlDAOFactory.getInstance().close();
}
	

	@Override
	public Producto get(long id) {
		// TODO Auto-generated method stub
		return null;
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

