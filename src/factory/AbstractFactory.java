package factory;

import java.sql.SQLException;

import dao.DaoCliente;
import dao.DaoFactura;
import dao.DaoFacturaProducto;
import dao.DaoProducto;
import interfaces.DAO;

public abstract class AbstractFactory {
	public static final int MYSQL_JDBC = 1;
	// public static final int DERBY_JDBC = 2;
	// public static final int JPA_HIBERNATE = 3;

	public abstract DaoFactura getDaoFactura() throws SQLException;

	public abstract DaoCliente getDaoCliente() throws SQLException;

	public abstract DaoFacturaProducto getDaoFacturaProducto() throws SQLException;

	public abstract DaoProducto getDaoProducto() throws SQLException;

	public static AbstractFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL_JDBC:
			return MysqlDAOFactory.getInstance();
		// case DERBY_JDBC: return new DerbyDAOFactory();
		// case JPA_HIBERNATE: ...
		default:
			return null;
		}
	}

}
