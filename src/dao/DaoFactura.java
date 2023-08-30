package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import entities.Factura;
import interfaces.DAO;

public class DaoFactura implements DAO<Factura>{
static Conexion conn = Conexion.getInstance();
	
	private static void createTable() {
		try {
			Connection conectar = conn.connect();
			String table = "CREATE TABLE factura(" + 
					"idFactura INT NOT NULL AUTO_INCREMENT," +
					"idCliente INT, " +
					"PRIMARY KEY (idFactura)," + 
					"FOREIGN KEY(idCliente)REFERENCES cliente(idCliente))";
					conectar.prepareStatement(table).execute();
				conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	
	@Override
	public void insert(Factura f) {
		try {
			if(f.getIdFactura() == null || f.getCliente().getIdCliente() == null) {
				throw new SQLException ("Debe ingresar una factura valida, con todos sus atributos");
			}
			Connection conectar = conn.connect();
			PreparedStatement insert = conectar.prepareStatement("INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)");
			
			insert.setInt(1, f.getIdFactura());
			insert.setInt(2, f.getCliente().getIdCliente());
			
			insert.executeUpdate();
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
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

	
	
	public static void main(String args[]) throws SQLException {
		DaoFactura factura = new DaoFactura();
		try {
			factura.createTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
