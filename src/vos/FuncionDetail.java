package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class FuncionDetail {


	@JsonProperty(value="numFuncion")
	private Integer numFuncion; 
	
	@JsonProperty(value="boletasVendidas")
	private Integer boletasVendidas; 
	
	@JsonProperty(value="porcentajeOcupacion")
	private Double porcentajeOcupacion;

	public FuncionDetail(@JsonProperty(value="numFuncion")Integer numFuncion, @JsonProperty(value="boletasVendidas")Integer boletasVendidas, @JsonProperty(value="porcentajeOcupacion") Double porcentajeOcupacion) {
		super();
		this.numFuncion = numFuncion;
		this.boletasVendidas = boletasVendidas;
		this.porcentajeOcupacion = porcentajeOcupacion;
	}

	public Integer getNumFuncion() {
		return numFuncion;
	}

	public void setNumFuncion(Integer numFuncion) {
		this.numFuncion = numFuncion;
	}

	public Integer getBoletasVendidas() {
		return boletasVendidas;
	}

	public void setBoletasVendidas(Integer boletasVendidas) {
		this.boletasVendidas = boletasVendidas;
	}

	public Double getPorcentajeOcupacion() {
		return porcentajeOcupacion;
	}

	public void setPorcentajeOcupacion(Double porcentajeOcupacion) {
		this.porcentajeOcupacion = porcentajeOcupacion;
	} 
	
	
	
}
