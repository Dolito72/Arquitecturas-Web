package interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.csv.CSVParser;

import entities.Cliente;

public interface DAO<T>{
	
		public   void insert(CSVParser datosT) throws SQLException ;
	    
	    public T get(long id);
	    
	    public  List getAll();
	    
	    public  void save(T t);
	    
	    public  void update(T t, String[] params);
	    
	    public  void delete(T t);
	}

	



