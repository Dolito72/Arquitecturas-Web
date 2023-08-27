package entities;

public class Factura {
	private int id;
	private Cliente cliente;
	
	public Factura( Cliente idCliente) {
		this.id = id;
		this.cliente = cliente;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cliente getIdCliente() {
		return cliente;
	}
	public void setIdCliente(Cliente idCliente) {
		this.cliente = idCliente;
	}
	
	public int idCliente() {
		return cliente.getId();
	}
	

}
