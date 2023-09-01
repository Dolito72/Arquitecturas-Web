import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import factory.*;

import dao.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class Main {
	private static DaoCliente DaoCliente;
	private static DaoFactura DaoFactura;
	private static DaoProducto DaoProducto;
	private static DaoFacturaProducto DaoFacturaProducto;
	

	public static void main(String[] args) throws SQLException, IOException  {
		
		MysqlDAOFactory mysql= MysqlDAOFactory.getInstance();
		DaoFactura = mysql.getDaoFactura();
		DaoCliente = mysql.getDaoCliente();
		DaoFactura = mysql.getDaoFactura();
		DaoCliente = mysql.getDaoCliente();
		 CSVParser datosFacturas = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/facturas.csv"));
		 CSVParser datosFacturasProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/facturas-productos.csv"));
		 CSVParser datosProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/productos.csv"));
		 CSVParser datosClientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/clientes.csv"));
	
		// DaoCliente.insert(datosClientes);
		// DaoFactura.insert(datosFacturas);
		// DaoProducto.insert(datosProductos);
	//	 DaoFacturaProducto.insert(datosFacturasProductos);
		 
		 System.out.println(DaoFactura.productoConMasRecaudacion());
		 System.out.println(DaoCliente.mejoresClientes());
		 
	
	}

}
