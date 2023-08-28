package entities;

public class Factura_Producto {
	private Integer id_factura;
	private Integer id_producto;
	private Integer cantidad;
	
	public Factura_Producto(Integer id_factura, Integer id_producto, Integer cantidad) {
		super();
		this.id_factura = id_factura;
		this.id_producto = id_producto;
		this.cantidad = cantidad;
	}

	public Integer getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	

}
