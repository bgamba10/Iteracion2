package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class SitioLocalidad {

	@JsonProperty(value="numeroFuncion")
	private int numeroFuncion;
	
	@JsonProperty(value="nombreSitio")
	private String nombreSitio;
	
	@JsonProperty(value="localidad")
	private String localidad;
	
	@JsonProperty(value="vendidasPublico")
	private int vendidasPublico;
	
	@JsonProperty(value="vendidasClientes")
	private int vendidasClientes;
	
	@JsonProperty(value="producidoPublico")
	private double producidoPublico;
	
	@JsonProperty(value="producidoClientes")
	private double producidoClientes;

	public SitioLocalidad(@JsonProperty(value="numeroFuncion") int numeroFuncion, @JsonProperty(value="nombreSitio") String nombreSitio, @JsonProperty(value="localidad") String localidad, @JsonProperty(value="vendidasPublico") int vendidasPublico, @JsonProperty(value="vendidasClientes") int vendidasClientes,
			@JsonProperty(value="producidoPublico") double producidoPublico, @JsonProperty(value="producidoClientes") double producidoClientes) {
		super();
		this.numeroFuncion = numeroFuncion;
		this.nombreSitio = nombreSitio;
		this.localidad = localidad;
		this.vendidasPublico = vendidasPublico;
		this.vendidasClientes = vendidasClientes;
		this.producidoPublico = producidoPublico;
		this.producidoClientes = producidoClientes;
	}

	public int getNumeroFuncion() {
		return numeroFuncion;
	}

	public void setNumeroFuncion(int numeroFuncion) {
		this.numeroFuncion = numeroFuncion;
	}

	public int getVendidasPublico() {
		return vendidasPublico;
	}

	public void setVendidasPublico(int vendidasPublico) {
		this.vendidasPublico = vendidasPublico;
	}

	public int getVendidasClientes() {
		return vendidasClientes;
	}

	public void setVendidasClientes(int vendidasClientes) {
		this.vendidasClientes = vendidasClientes;
	}

	public String getNombreSitio() {
		return nombreSitio;
	}

	public void setNombreSitio(String nombreSitio) {
		this.nombreSitio = nombreSitio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public double getProducidoPublico() {
		return producidoPublico;
	}

	public void setProducidoPublico(double producidoPublico) {
		this.producidoPublico = producidoPublico;
	}

	public double getProducidoClientes() {
		return producidoClientes;
	}

	public void setProducidoClientes(double producidoClientes) {
		this.producidoClientes = producidoClientes;
	}

	
	
	
}
