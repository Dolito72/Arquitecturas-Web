package factory;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.csv.CSVParser;

import interfaces.DAO;

public class MySQLDAO implements DAO {

	@Override
	public void insert(CSVParser datosT) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Object get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Object t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object t, String[] params) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object t) {
		// TODO Auto-generated method stub

	}

}
