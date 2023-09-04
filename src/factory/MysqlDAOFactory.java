package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import dao.DaoCliente;
import dao.DaoFactura;
import dao.DaoFacturaProducto;
import dao.DaoProducto;
import interfaces.DAO;
import utils.Helper;

public class MysqlDAOFactory extends AbstractFactory {
	private static Connection conn;
	private static MysqlDAOFactory instancia;
	private static final String URL = "jdbc:mysql://localhost:3306/dao";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	private MysqlDAOFactory () {
		
	}

	@Override
	public DaoFactura getDaoFactura() throws SQLException {
		return new DaoFactura(conn);
	}
	@Override
	public DaoCliente getDaoCliente() throws SQLException {
		return new DaoCliente(conn);
	}
	@Override
	public DaoFacturaProducto getDaoFacturaProducto() throws SQLException {
		return new DaoFacturaProducto(conn);
	}
	@Override
	public DaoProducto getDaoProducto() throws SQLException {
		return new DaoProducto(conn);
	}
	
	public static MysqlDAOFactory getInstance() {
		if(instancia == null) {
			instancia = new MysqlDAOFactory();
		}
		return instancia;
	}
		
		//abrir conexion
	public Connection connect() throws SQLException {
		try {
			
			Class.forName(DRIVER).getDeclaredConstructor().newInstance();
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			return conn;
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			
			e.printStackTrace();
			System.exit(1);
		}
		return conn;
		
	} 
	
	//cerrar conexion
	public void close() throws SQLException {
		try {
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			conn.close();
			System.exit(2);
			
		}finally {
			conn.close();
		}
	}

}
