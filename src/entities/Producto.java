package entities;

public class Producto {
	private Integer idProducto;
	private String nombre;
	private Float valor;
	
	public Producto(Integer id, String nombre, Float valor) {
	
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.valor = valor;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	

}
