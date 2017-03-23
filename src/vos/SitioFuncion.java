package vos;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class SitioFuncion {

	@JsonProperty(value="sitio")
	private String sitio; 
	
	@JsonProperty(value="funciones")
	private List<FuncionDetail> funciones;

	public SitioFuncion(@JsonProperty(value="sitio")String sitio, @JsonProperty(value="funciones") List<FuncionDetail> funciones) {
		super();
		this.sitio = sitio;
		this.funciones = funciones;
	}

	public String getSitio() {
		return sitio;
	}

	public void setSitio(String sitio) {
		this.sitio = sitio;
	}

	public List<FuncionDetail> getFunciones() {
		return funciones;
	}

	public void setFunciones(List<FuncionDetail> funciones) {
		this.funciones = funciones;
	} 
	
	
}
