package dto;

public class DtoProducto {

	private Integer idProducto;
	private String name;
	private Integer totalFacturacion;
	public DtoProducto(Integer idProducto, String name, Integer totalFacturacion) {
		this.idProducto = idProducto;
		this.name = name;
		this.totalFacturacion = totalFacturacion;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public String getName() {
		return name;
	}
	public Integer getTotalFacturacion() {
		return totalFacturacion;
	}
	@Override
	public String toString() {
		return "DtoProducto [idProducto=" + idProducto + ", name=" + name + ", totalFacturacion=" + totalFacturacion
				+ "]";
	} 
	
	
}
