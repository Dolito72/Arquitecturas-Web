package factory;


import interfaces.DAO;

public abstract class  AbstractFactory {
	

	public static final int MYSQL_JDBC = 1;
	//public static final int DERBY_JDBC = 2;
//	public static final int JPA_HIBERNATE = 3;
	 
	public abstract DAO getDAO();
	
	public static AbstractFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
			case MYSQL_JDBC : return new MySQLDAOFactory();
			//case DERBY_JDBC: return new DerbyDAOFactory();			//case JPA_HIBERNATE: ...
			default: return null;
		}
	}
	
	
	
	

}
