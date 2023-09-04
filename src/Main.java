
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import factory.*;
import utils.Helper;
import dao.*;
import dto.DtoCliente;
import dto.DtoProducto;
import entities.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class Main {
	private static DaoCliente DaoCliente;
	private static DaoFactura DaoFactura;
	private static DaoProducto DaoProducto;
	private static DaoFacturaProducto DaoFacturaProducto;
	

	public static void main(String[] args) throws SQLException, IOException  {
		
		CSVParser datosFacturas = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/facturas.csv"));
		CSVParser datosFacturasProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/facturas-productos.csv"));
		CSVParser datosProductos = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/productos.csv"));
		CSVParser datosClientes = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/dataset/clientes.csv"));
		
		
		
		Helper helper = new Helper();
		//helper.createTables();
		//helper.fillTables(datosFacturas, datosFacturasProductos, datosProductos, datosClientes);

		MysqlDAOFactory mysql= MysqlDAOFactory.getInstance();
		
		
		DaoFactura = mysql.getDaoFactura();
		DaoCliente = mysql.getDaoCliente();
		DaoFactura = mysql.getDaoFactura();
		DaoCliente = mysql.getDaoCliente();
		
		
		DtoProducto p = DaoFactura.productoConMasRecaudacion();
		System.out.println("---------- Producto con mas recaudacion ----------");
		System.out.println("Id Producto\t Nombre \t\t Valor");
		System.out.println("   "+p.getIdProducto() +"\t \t " +p.getName() +"\t\t $" + p.getTotalFacturacion());
		
		
		ArrayList<DtoCliente> dt = DaoCliente.mejoresClientes();
		System.out.println("\n ---------- Listado Clientes segun facturacion ----------");
		System.out.println("Facturacion\t Id Cliente\t    Nombre");
		for(DtoCliente e:dt) {
			System.out.println(" $" + e.getFacturacion() +"\t\t     " + e.getIdCliente() +"\t\t    " +e.getName());
		}
		 
	}
}
