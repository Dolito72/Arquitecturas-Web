package interfaces;

import java.util.List;
import java.util.Optional;

import entities.Cliente;

public interface DAO<T>{
	
		public   void insert(T t);
	    
	    public T get(long id);
	    
	    public  List getAll();
	    
	    public  void save(T t);
	    
	    public  void update(T t, String[] params);
	    
	    public  void delete(T t);
	}

	



