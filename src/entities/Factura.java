package entities;

public class Factura {
	private Integer idFactura;
	private Cliente cliente;
	
	public Factura( Integer idFactura, Cliente idCliente) {
		this.idFactura = idFactura;
		this.cliente = cliente;
	}
	public Integer getIdFactura() {
		return idFactura;
	}
	public void setId(int id) {
		this.idFactura = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setIdCliente(Cliente idCliente) {
		this.cliente = idCliente;
	}
	
	

}
