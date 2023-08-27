package interfaces;

import java.util.List;
import java.util.Optional;

public interface DAO<T>{
	
	
	public interface Dao<T> {
	    
	    public abstract Optional<T> get(long id);
	    
	    public abstract List<T> getAll();
	    
	    public abstract void save(T t);
	    
	    public abstract void update(T t, String[] params);
	    
	    public abstract void delete(T t);
	}

}
