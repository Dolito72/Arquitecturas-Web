package dto;

public class DtoCliente {
	private Integer idCliente;
	private String name;
	private Integer facturacion;
	
	public DtoCliente(Integer idCliente, String name, Integer facturacion) {
		this.idCliente = idCliente;
		this.name = name;
		this.facturacion = facturacion;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public String getName() {
		return name;
	}

	public Integer getFacturacion() {
		return facturacion;
	}

	@Override
	public String toString() {
		return "DtoCliente idCliente = " + idCliente + ", name = " + name + ", facturacion = " + facturacion + " \n  ";
	}
	
	

}
