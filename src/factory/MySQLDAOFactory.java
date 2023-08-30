package factory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.Conexion;
import dao.DaoCliente;
import dao.DaoFactura;
import dao.DaoFacturaProducto;
import dao.DaoProducto;
import interfaces.DAO;

public class MySQLDAOFactory extends AbstractFactory {
	//guardamos  el estado de la conexion
			private static Connection conexion;
		//variable para crear una sola instancia
			private static Conexion instancia;
		//variables para conectarse a  la bd
			private static final String URL = "jdbc:mysql://localhost:3306/dao";
			private static final String USERNAME = "root";
			private static final String PASSWORD = "";
			//constructor privado para que no puedan hacer un new
			//private Conexion() {
			//}
			//abrir conexion
			public Connection connect() throws SQLException {
				String driver = "com.mysql.cj.jdbc.Driver";
				try {
					
					Class.forName(driver).getDeclaredConstructor().newInstance();
					conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);
					System.out.println("conexion exitosa");
					return conexion;
					
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
					
					e.printStackTrace();
					System.exit(1);
				}
				return conexion;
			}
			//cerrar conexion
			public void close() throws SQLException {
				try {
					conexion.close();
					System.out.println("cerro conexion");
				}catch(Exception e) {
					e.printStackTrace();
					conexion.close();
					System.exit(2);
					
				}finally {
					conexion.close();
				}
			}
			
			//patron singleton
			public static Conexion getInstance() {
				if(instancia == null) {
					instancia = new Conexion();
				}
				return instancia;
			}
			public static void main (String args[]) throws SQLException {
				Conexion conn = new Conexion();
				conn.connect();
			}
	//		@Override
		//	public DAO getDAO() {
				// TODO Auto-generated method stub
		//		return new MySQLDAO;
//			}
			@Override
			public DAO getDAO() {
				// TODO Auto-generated method stub
				return null;
			}



}
